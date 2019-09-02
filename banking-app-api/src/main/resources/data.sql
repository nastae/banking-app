insert into user_entity (unique_user_id, email, password)
values (10, 'client@gmail.com', 'password');
insert into transaction_entity (unique_transaction_id, amount, direction, user)
values (20, '20.20', 'DEPOSIT', 10);
insert into transaction_entity (unique_transaction_id, amount, direction, user)
values (30, '20.50', 'DEPOSIT', 10);