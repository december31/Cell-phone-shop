alter table role_permissions
add primary key (role_id, permissions_id)

insert into category (id, name)
values (0, 'head phone');
insert  into category (id, name)
values (1, 'smart phone');
insert  into category (id, name)
values (2, 'phone case');

insert  into role (id, name)
values (0, 'admin');
insert  into role (id, name)
values (1, 'editor');
insert  into role (id, name)
values (2, 'user');


insert  into permission (id, name)
values (0, 'read');
insert  into permission (id, name)
values (1, 'delete');
insert  into permission (id, name)
values (2, 'edit');

insert  into role_permissions (role_id, permissions_id)
values
    (0, 0),
    (0, 1),
    (0, 2),
    (1, 0),
    (1, 1),
    (1, 2),
    (2, 0);

insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (0, N'asset/image/device/s22ultra.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300    .000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (1, N'asset/image/device/11t.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (2, N'asset/image/device/a13.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (3, N'asset/image/device/a53.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (4, N'asset/image/device/a73.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (5, N'asset/image/device/c9.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (6, N'asset/image/device/ip13prm.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (7, N'asset/image/device/m52.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (8, N'asset/image/device/oppo.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (9, N'asset/image/device/redmi11.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (10, N'asset/image/device/xm12.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 1);

-- headphone
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (11, N'asset/image/device/head-phone-1.jpg', N'Tai nghe Chụp Tai Không Dây Sony WH-CH510', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 0);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (12, N'asset/image/device/head-phone-2.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 0);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (13, N'asset/image/device/head-phone-3.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 0);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (14, N'asset/image/device/head-phone-4.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 0);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (15, N'asset/image/device/head-phone-5.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 0);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (16, N'asset/image/device/head-phone-6.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 0);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (17, N'asset/image/device/head-phone-7.jpg', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 0);


-- case
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (17, N'asset/image/device/case1.jpg', N'Bao da Apple Smart Folio cho iPad Pro 11 2021 chính hãng', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 2);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (18, N'asset/image/device/case2.jpg', N'Bao da Apple Smart Folio cho iPad Pro 11 2021 chính hãng', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 2);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (19, N'asset/image/device/case3.jpg', N'Bao da Apple Smart Folio cho iPad Pro 11 2021 chính hãng', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 2);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (20, N'asset/image/device/case4.jpg', N'Bao da Apple Smart Folio cho iPad Pro 11 2021 chính hãng', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 2);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (21, N'asset/image/device/case5.jpg', N'Bao da Apple Smart Folio cho iPad Pro 11 2021 chính hãng', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 2);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (22, N'asset/image/device/case6.jpg', N'Bao da Apple Smart Folio cho iPad Pro 11 2021 chính hãng', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 2);
insert  INTO product (id, image_url, name, price, description, ratting, category_id)
VALUES (23, N'asset/image/device/case7.jpg', N'Bao da Apple Smart Folio cho iPad Pro 11 2021 chính hãng', 29990000,
        N'hỗ trợ cũ đổi mới, trợ giá lên đến 300.000đ', 4.5, 2);



insert  INTO [user] (id, username, email, password, role_id)
VALUES (0, 'admin', 'admin@admin.admin', '1339erirag842muui25t9nn1bnthcuksgsvs9esag7rf5aq4ha8o', 0);


TRUNCATE TABLE hibernate_sequence;

insert INTO hibernate_sequence (next_val)
VALUES (24);

