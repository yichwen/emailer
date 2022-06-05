INSERT INTO MAIL_PROVIDER (`name`, `priority`, `status`, `url`) 
VALUES ('MyEmail', 1, 'ACTIVE', 'http://192.168.99.100:9001/api/v1/provider-service');

INSERT INTO MAIL_PROVIDER_PARAM (`param_key`, `param_value`, `type`, `mail_provider_id`) 
VALUES ('api-key', 'owgwoi9202bg23', 'HEADER', 1);

INSERT INTO MAIL_PROVIDER (`name`, `priority`, `status`, `url`) 
VALUES ('MyEmail v2', 1, 'ACTIVE', 'http://192.168.99.100:9002/api/v1/provider-service');

INSERT INTO MAIL_PROVIDER_PARAM (`param_key`, `param_value`, `type`, `mail_provider_id`)
VALUES ('api-key', 'sdibviuw983', 'HEADER', 2);