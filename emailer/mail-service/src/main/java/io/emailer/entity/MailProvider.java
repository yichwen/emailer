package io.emailer.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Data
@Entity
@Table(name = "mail_provider")
public class MailProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long priority;
    private String url;
    @Enumerated(EnumType.STRING)
    private MailProviderStatus status;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "mailProvider", fetch = FetchType.LAZY)
    private Set<MailProviderParam> params = new HashSet<>();
}
