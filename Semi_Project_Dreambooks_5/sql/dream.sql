--======================================================
-- DreamBooks
--======================================================
--관리자계정으로 dream계정 생성

create user dream
identified by dream
default tablespace users;

grant connect, resource to dream;
show user;

--계정 삭제 후 다시 만들 때 사용할 것
drop user dream cascade;

--dream계정으로 테이블 생성-----------------------------------------------------------

--USER TABLE
--아이디(P(UNIQUE,NOT NULL)), 패스워드(NOT NULL), 이름(NOT NULL), 
--이메일, 전화번호(NOT NULL), 권한(NOT NULL, U|A|P)
create table users (
    user_id varchar2(15),
    password varchar2(300) not null,
    user_name varchar2(30) not null,
    user_role char(1) default 'U' not null,
    email varchar2(100) not null,
    phone char(11),
    quit_yn char(1) default 'N' not null,
    constraints pk_user_id primary key(user_id),
    constraints ck_user_role check(user_role in ('U','A','P')),
    constraints ck_user_quit check(quit_yn in ('N','Y'))
    );

select * from users;

--CATEGORY TABLE
create table category(
    category_code varchar2(20) not null,
    category_name varchar2(30),
    category_level number default 1,
    category_ref number,
    constraints pk_category_code primary key(category_code)
    );
----카테고리 코드를 위한 시퀀스
create sequence seq_category;

select * from category;

--AUTHOR TABLE
create table author(
    author_code varchar2(20) not null,
    author_name varchar2(50),
    author_content varchar2(2000),
    constraints pk_author_code primary key(author_code)
    );
--저자코드 시퀀스
create sequence seq_author;   

--BOOK TABLE
create table book(
    book_no char(13) not null,
    --ISBN
    user_id varchar2(15) not null,
    book_title varchar2(200) not null,
    price number not null,
    book_content varchar2(2000) not null,
    author_code varchar2(20) not null,
    category_code varchar2(20),
    book_tag_code varchar2(20),
    book_img_original_filename varchar2(100),
    book_img_rename_filename varchar2(100),
    sale_count number default 0,
    display_book char(1) default 'N' not null,
    book_file_original_name varchar2(100),
    book_file_renamed_name varchar2(100),
    constraints pk_book_no primary key (book_no),
    constraints fk_category_code foreign key(category_code) 
                                 references category(category_code) 
                                 on delete set null,
     constraints fk_user_id foreign key(user_id) 
                                  references users(user_id) 
                                  on delete cascade,
    constraints fk_author_code foreign key(author_code) 
                                  references author(author_code) 
                                  on delete set null,
    constraints ck_display check(display_book in ('N','Y'))
);

----AUTHOR_BOOK TABLE
--create table author_book(
--    book_no char(13) not null,
--    author_code varchar2(20) not null,
--    constraints pk_author_book primary key(book_no, author_code),
--    constraints fk_author_book1 foreign key(book_no) references book(book_no),
--    constraints fk_ahthor_book2 foreign key(author_code) references author(author_code)
--    );
--
--select * from author_book;

--COMMENTS TABLE
--한줄평
create table comments(
    comment_no number not null,
    comment_content varchar2(2000) not null,
    comment_date date default sysdate,
    book_no char(13) not null,
    user_id varchar2(15) not null,
    constraints pk_comment_no primary key(comment_no),
    constraints fk_book_no1 foreign key (book_no) references book(book_no) on delete cascade,
    constraints fk_user_id1 foreign key (user_id) references users(user_id) on delete cascade
    );
    
--한줄평_코드를 위한 시퀀스
create sequence seq_comments;

--ORDER TABLE
create table orders(
    order_no varchar2(17),
    user_id varchar2(15),
    order_date date default sysdate,
    cancel_yn char(1) default 'N',
    constraints pk_order primary key(order_no),
    constraints fk_user_id2 foreign key(user_id) 
                            references users(user_id),
    constraints ck_cancel check(cancel_yn in ('N','Y'))
);

--ORDER_BOOK TABLE
create table order_book(
    order_book_no number,
    order_no varchar2(17) not null,
    book_no char(13) not null,
    constraints pk_order_book_no primary key (order_book_no), 
    constraints fk_order_no3 foreign key(order_no)
                            references orders(order_no)
                            on delete cascade,
    constraints fk_book_no2 foreign key(book_no)
                            references book(book_no)
                            on delete set null
);
--order_book_no를 위한 시퀀스
create sequence seq_orderbook;

--CART TABLE
create table cart(
    cart_code varchar2(20) not null,
    book_no char(13) not null,
    user_id varchar2(15) not null,
    cart_total_price number,
    constraints pk_cart primary key(cart_code),
    constraints fk_book_no4 foreign key(book_no) references book(book_no) on delete cascade,
    constraints fk_user_id3 foreign key(user_id) references users(user_id) on delete cascade
    );
--cart_code를 위한 시퀀스
create sequence seq_cart;

--TAG TABLE
create table tag(
    tag_code varchar2(20) not null,
    tag_name varchar2(50),
    book_no char(13) not null,
    user_id varchar2(15) not null,
    constraints pk_tag_code primary key(tag_code),
    constraints fk_book_no5 foreign key(book_no) references book(book_no) on delete cascade,
    constraints fk_user_id4 foreign key(user_id) references users(user_id) on delete cascade
    );
--tag_code를 위한 시퀀스
create sequence seq_tag;

commit;