package io.emailer.service.impl;

import io.emailer.dto.MailDetailsDto;
import io.emailer.dto.MailDto;
import io.emailer.entity.Mail;
import io.emailer.entity.MailProvider;
import io.emailer.entity.MailStatus;
import io.emailer.exception.MailOperationException;
import io.emailer.exception.MailUndeliverableException;
import io.emailer.exception.NoAvailableMailProviderException;
import io.emailer.repository.MailRepository;
import io.emailer.service.MailProviderExecutor;
import io.emailer.service.MailProviderService;
import io.emailer.service.MailService;
import io.emailer.util.P;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    
    @Autowired
    private MailRepository mailRepository;
    
    @Autowired
    private MailProviderService mailProviderService;
    
    @Override
    public void sendMail(MailDto mailDto) {
        List<MailProviderExecutor> mailProviderExecutors = mailProviderService.getMailProviderExecutors();
        int numberOfProviders = mailProviderExecutors.size();
        if (numberOfProviders == 0) {
            saveMail(mailDto, MailStatus.PENDING, null);
            return;
        }
        MailProviderExecutor executor = null;
        MailStatus status = MailStatus.PENDING;
        int index = 0;
        while (index < numberOfProviders) {
            executor = mailProviderExecutors.get(index);
            try {
                if (executor != null) {
                    executor.sendMail(mailDto);
                    status = MailStatus.SUCCESS;
                    // if success, break
                    break;
                }
                index++;
            } catch (MailOperationException e) {
                status = MailStatus.FAILED;
                index++;
            }
        }
        
        if (MailStatus.SUCCESS == status) {
            saveMail(mailDto, status, executor.getMailProvider());
        } else {
            saveMail(mailDto, status, null);
            if (MailStatus.FAILED == status) {
                throw new MailUndeliverableException();
            } else {
                throw new NoAvailableMailProviderException();
            }
        }
    }

    @Override
    @Transactional
    public P<MailDetailsDto> getMails(Pageable pageable) {
        Page<Mail> mails = mailRepository.findAll(pageable);
        return P.getInstance(mails, (mail) -> {
            MailDetailsDto mailDetailsDto = new MailDetailsDto();
            BeanUtils.copyProperties(mail, mailDetailsDto);
            if (mail.getMailProvider() != null) {
                mailDetailsDto.setMailProvider(mail.getMailProvider().getName());
            }
            return mailDetailsDto;
        });
    }

    private void saveMail(MailDto mailDto, MailStatus status, MailProvider mailProvider) {
        Mail mail = new Mail();
        BeanUtils.copyProperties(mailDto, mail);
        mail.setStatus(status);
        if (status == MailStatus.SUCCESS && mailProvider != null) {
            mail.setMailProvider(mailProvider);
        }
        mailRepository.save(mail);
    }
    
}