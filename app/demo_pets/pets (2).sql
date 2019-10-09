-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 09, 2019 lúc 06:21 PM
-- Phiên bản máy phục vụ: 10.1.37-MariaDB
-- Phiên bản PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `pets`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baiviet`
--

CREATE TABLE `baiviet` (
  `id` int(11) NOT NULL,
  `id_user` varchar(200) NOT NULL,
  `id_pet` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `timeup` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `baiviet`
--

INSERT INTO `baiviet` (`id`, `id_user`, `id_pet`, `title`, `description`, `timeup`) VALUES
(1, 'admin', 1, 'Đây là con mèo mới của tôi!', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy', '2019-10-08 17:00:00'),
(2, 'anhhuu', 2, 'Đây là con mèo thứ 2 của tôi!.You like it?', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy', '2019-10-08 17:00:00'),
(3, 'anhhuu', 3, 'Đây là con mèo thứ 2 của tôi!.You like it?', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', '2019-10-08 17:00:00'),
(4, 'member', 4, 'Ai lai kịt!', 'It is a long established fact that a reader will be distracted by the readable content', '2019-10-08 17:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `id_baiviet` int(11) NOT NULL,
  `id_user` varchar(200) NOT NULL,
  `comment` text NOT NULL,
  `date` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `comment`
--

INSERT INTO `comment` (`id`, `id_baiviet`, `id_user`, `comment`, `date`) VALUES
(1, 1, 'admin', 'Không có gì!', '2019-10-09'),
(2, 1, 'admin', 'Hay quá cơ', '2019-10-09'),
(3, 1, 'admin', '1+1=2-1=4-3=1+2', '09-10-2019');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `noti`
--

CREATE TABLE `noti` (
  `id` int(11) NOT NULL,
  `cate_noti` int(11) NOT NULL,
  `mess` text NOT NULL,
  `user_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `noti`
--

INSERT INTO `noti` (`id`, `cate_noti`, `mess`, `user_id`) VALUES
(1, 1, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed', ''),
(2, 2, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s', ''),
(3, 3, 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn\'t anything', ''),
(4, 1, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pet`
--

CREATE TABLE `pet` (
  `id` int(22) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `species` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `breed` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(11) NOT NULL,
  `birth` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `love` tinyint(1) DEFAULT NULL,
  `value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `pet`
--

INSERT INTO `pet` (`id`, `name`, `species`, `breed`, `gender`, `birth`, `picture`, `love`, `value`, `message`) VALUES
(1, 'cat', 'long ngan', 'meo anh', 1, '28-18-2019', '/demo_pets/pets_picture/1.jpeg', NULL, NULL, 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, con'),
(2, 'cat', 'Mèo lông dài', 'Mèo em', 1, '28-18-2019', '/demo_pets/pets_picture/2.jpeg', NULL, NULL, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing'),
(3, 'cat', 'Mèo lông dài', 'Mèo em', 1, '28-18-2019', '/demo_pets/pets_picture/6.jpeg', NULL, NULL, 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn\'t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reason'),
(4, 'cat', 'long ngan', 'meo anh', 1, '28-18-2019', '/demo_pets/pets_picture/7.jpeg', NULL, NULL, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `fullname` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `roles` tinyint(1) NOT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `face` varchar(100) NOT NULL,
  `birth` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `email`, `phone`, `address`, `roles`, `avatar`, `face`, `birth`) VALUES
('admin', '1234567', 'Hữu Mass', 'robotmeo@gmail.com', '023456789', '329 - Hà Nội ', 1, NULL, 'OmyGod@yahoo.com.vn', '20/09/1999'),
('anhhuu', '123456', 'DR.MONN', 'robotmeobg@gmail.com ', '0599853354', '', 0, NULL, 'OmyChuoi@yahoo.com.vn', '20/09/1999'),
('member', '123456', 'Minh nuti', 'membermin@yahoo.com.vn', '012364987', '123 - TP.HCM', 0, NULL, 'Obama@feedback.com', '20/09/1999'),
('qwer', '123456', 'Kem tươi', 'rongbg@gmail.com ', '096845333', '', 0, NULL, '', '20/09/1999');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baiviet`
--
ALTER TABLE `baiviet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_pet` (`id_pet`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_baiviet` (`id_baiviet`),
  ADD KEY `id_user` (`id_user`);

--
-- Chỉ mục cho bảng `noti`
--
ALTER TABLE `noti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `baiviet`
--
ALTER TABLE `baiviet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `noti`
--
ALTER TABLE `noti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `pet`
--
ALTER TABLE `pet`
  MODIFY `id` int(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `baiviet`
--
ALTER TABLE `baiviet`
  ADD CONSTRAINT `baiviet_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `baiviet_ibfk_2` FOREIGN KEY (`id_pet`) REFERENCES `pet` (`id`);

--
-- Các ràng buộc cho bảng `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_baiviet`) REFERENCES `baiviet` (`id`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
