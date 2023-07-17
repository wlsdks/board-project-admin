-- 테스트 계정
-- TODO: 테스트용이지만 비밀번호가 노출된 데이터 세팅. 개선하는 것이 좋을 지 고민해 보자.
insert into user_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('wlsdks12', '{noop}wlsdks12', 'ADMIN', 'wlsdks12', 'wlsdks12@mail.com', 'I am jinan.', now(), 'wlsdks12', now(), 'wlsdks12')
;

insert into user_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('uno', '{noop}wlsdks12', 'ADMIN', 'Uno', 'uno@mail.com', 'I am Uno.', now(), 'uno', now(), 'uno')
;

insert into user_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('mark', '{noop}asdf1234', 'MANAGER', 'Uno2', 'mark@mail.com', 'I am mark.', now(), 'mark', now(), 'mark')
;

insert into user_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('susan', '{noop}asdf1234','MANAGER,DEVELOPER', 'susan', 'susan@mail.com', 'I am susan.', now(), 'susan', now(), 'susan')
;

insert into user_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('jim', '{noop}asdf1234', 'USER', 'jim', 'jim@mail.com', 'I am jim.', now(), 'jim', now(), 'jim')
;

