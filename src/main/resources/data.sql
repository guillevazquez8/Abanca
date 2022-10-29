DELETE FROM abanca.account_holders;
DELETE FROM abanca.address;
DELETE FROM abanca.user;

INSERT INTO abanca.address (id, street, number, postcode, city, country) VALUES
(1, 'Rua Canido', 76, 36390, 'Vigo', 'España'),
(2, 'Taquigraf Garriga', 91, 08029, 'Barcelona', 'España'),
(3, 'Valencia', 184, 08011, 'Barcelona', 'España');

INSERT INTO abanca.user (id, name) VALUES
(1, 'Guille'),
(2, 'Chema'),
(3, 'Brais');

INSERT INTO abanca.account_holders (id, date_of_birth, primary_address_id, mailing_address_id) VALUES
(1, '1995-09-28', 1, null),
(2, '1993-11-21', 2, null),
(3, '1990-01-06', 3, null);