-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 11, 2019 lúc 11:31 PM
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
  `timeup` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `baiviet`
--

INSERT INTO `baiviet` (`id`, `id_user`, `id_pet`, `title`, `description`, `timeup`) VALUES
(1, 'admin', 1, 'Đây là con mèo mới của tôi!', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy', '2019-10-09 00:00:00'),
(2, 'anhhuu', 2, 'Đây là con mèo thứ 2 của tôi!.You like it?', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy', '2019-10-09 00:00:00'),
(3, 'anhhuu', 3, 'Đây là con mèo thứ 2 của tôi!.You like it?', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', '2019-10-09 00:00:00'),
(4, 'member', 4, 'Ai lai kịt!', 'It is a long established fact that a reader will be distracted by the readable content', '2019-10-09 00:00:00'),
(12, 'member', 26, 'Đây là con mèo mới của tôi!', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy', '2019-10-09 00:00:00'),
(13, 'jonkey', 27, 'Đây là con mèo thứ 2 của tôi!.You like it?', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy', '2019-10-09 00:00:00'),
(14, 'anhhuu', 28, 'Đây là con mèo thứ 7 của tôi!.You like it?', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', '2019-10-09 00:00:00'),
(15, 'member', 29, 'It is a long established fact that a reader will be distracted', 'It is a long established fact that a reader will be distracted by the readable content', '2019-10-09 00:00:00'),
(16, 'admin', 30, 'Đây là con mèo thứ 2 của tôi!.You like it?Lorem Ipsum has been the industry\'s standard dummy', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy', '2019-10-09 00:00:00'),
(17, 'anhhuu', 31, 'Đây là con mèo thứ 7 của tôi!.You like it? Look like that typesetting industry.', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', '2019-10-09 00:00:00'),
(18, 'member', 33, 'It typesetting industry. is a long established fact that a reader will be distracted', 'It is a long established fact that a reader will be distracted by the readable content', '2019-10-09 00:00:00');

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
(85, 1, 'member', 'ốc chó', '11-10-2019'),
(86, 1, 'member', 'ngu như bò ', '11-10-2019'),
(87, 1, 'member', 'ko quan tâm ', '11-10-2019'),
(88, 1, 'member', 'testing onlike', '11-10-2019'),
(89, 4, 'member', 'alopppppp iu', '11-10-2019'),
(90, 4, 'member', 'why dont run', '11-10-2019'),
(91, 1, 'member', '????', '11-10-2019'),
(92, 1, 'member', '????????????????????????????', '11-10-2019'),
(93, 1, 'member', '????????????????????????????', '11-10-2019'),
(94, 1, 'member', '????????????????????????????', '11-10-2019'),
(95, 1, 'yyyyy', 'oooooooo', '11-10-2019'),
(96, 1, 'yyyyy', 'oooooooo', '11-10-2019'),
(97, 1, 'yyyyy', 'gone', '11-10-2019'),
(98, 1, 'yyyyy', 'gone', '11-10-2019'),
(99, 1, 'yyyyy', 'gone', '11-10-2019'),
(100, 1, 'yyyyy', 'gone', '11-10-2019'),
(101, 2, 'yyyyy', 'uuuhhjj', '11-10-2019'),
(102, 2, 'yyyyy', 'uuuhhjj', '11-10-2019'),
(103, 2, 'yyyyy', 'uuuhhjj', '11-10-2019'),
(104, 1, 'yyyyy', 'ngu lozzz', '11-10-2019'),
(105, 1, 'yyyyy', 'ngu lozzz', '11-10-2019'),
(106, 1, 'yyyyy', 'ngu lozzz', '11-10-2019'),
(107, 1, 'yyyyy', 'ngu lozzz', '11-10-2019'),
(108, 1, 'yyyyy', 'hhhhhhhh', '11-10-2019'),
(109, 1, 'yyyyy', 'uuuuu', '11-10-2019'),
(110, 1, 'yyyyy', 'hhh', '11-10-2019'),
(111, 1, 'yyyyy', 'hhhjh', '11-10-2019'),
(112, 1, 'yyyyy', 'hhh', '11-10-2019'),
(113, 1, 'yyyyy', 'hhffgh', '11-10-2019'),
(114, 1, 'yyyyy', 'hhhhh', '11-10-2019'),
(115, 1, 'yyyyy', 'jjjjj', '11-10-2019'),
(116, 1, 'yyyyy', 'jjjjj', '11-10-2019'),
(117, 1, 'yyyyy', 'jjjjjjj', '11-10-2019'),
(118, 1, 'yyyyy', 'hhhhhhhh', '11-10-2019'),
(119, 2, 'yyyyy', 'jjjjjj', '11-10-2019'),
(120, 1, 'yyyyy', 'hhhhh', '11-10-2019'),
(121, 1, 'yyyyy', 'hhhhh', '11-10-2019'),
(122, 4, 'yyyyy', 'j', '11-10-2019'),
(123, 1, 'yyyyy', 'jjjjgghhjj', '11-10-2019'),
(124, 1, 'yyyyy', 'jjjjgghhjj chip', '11-10-2019'),
(125, 1, 'yyyyy', 'hhhhfgl', '11-10-2019'),
(126, 1, 'yyyyy', 'hhhh', '11-10-2019'),
(127, 1, 'admin', 'hhhh', '11-10-2019'),
(128, 1, 'admin', 'ngu', '11-10-2019'),
(129, 1, 'admin', '66666', '11-10-2019');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `noti`
--

CREATE TABLE `noti` (
  `id` int(11) NOT NULL,
  `cate_noti` int(11) NOT NULL,
  `mess` text NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `baiviet_id` int(11) NOT NULL,
  `dateup` varchar(200) NOT NULL,
  `user_bi_like` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `noti`
--

INSERT INTO `noti` (`id`, `cate_noti`, `mess`, `user_id`, `baiviet_id`, `dateup`, `user_bi_like`) VALUES
(1, 1, 'đã comment bài viết của bạn', 'admin', 1, '2019-10-23', 'member'),
(2, 2, 'đã like bài viết của bạn', 'member', 1, '2019-10-12', 'admin'),
(3, 3, 'đã comment bài viết của bạn', 'admin', 1, '2019-10-12', 'member'),
(4, 1, 'đã comment bài viết của bạn', 'anhhuu', 1, '2019-10-12', 'member'),
(5, 2, 'đã like bài viết của bạn', 'anhhuu', 1, '2019-10-12', 'admin'),
(6, 2, 'đã like bài viết của bạn', 'qwer', 1, '2019-10-12', 'admin'),
(7, 0, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'member', 1, '2019-10-16', 'admin'),
(8, 0, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'member', 1, '0000-00-00', 'admin'),
(9, 0, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'member', 1, '11-10-2019', 'admin'),
(10, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 1, '11-10-2019', 'admin'),
(11, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 1, '11-10-2019', 'admin'),
(12, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 1, '11-10-2019', 'admin'),
(13, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 1, '11-10-2019', 'admin'),
(14, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 1, '11-10-2019', 'admin'),
(15, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019', 'admin'),
(16, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019', 'member'),
(17, 213, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019', 'member'),
(18, 754, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019', 'member'),
(19, 166, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019', 'member'),
(20, 532, 'đã comment bài viết của bạn!', 'yyyyy', 1, '11-10-2019', 'admin'),
(21, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 0101:1010:0909', 'member'),
(22, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 01:52:02', 'member'),
(23, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 13:52:37', 'member'),
(24, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 012019000000Fri, 11 Oct 2019 13:53:42 +020042:53:42', 'member'),
(25, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 01:54:35', 'member'),
(26, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 01:55:01pm', 'member'),
(27, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 01:58:52pm', 'member'),
(28, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 06:59:29pm', 'member'),
(29, 1, 'Có ai vừa nghe nhạc vừa lướt đọc bình luận', 'yyyyy', 2, '11-10-2019 07:00:07pm', 'admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pet`
--

CREATE TABLE `pet` (
  `id` int(22) NOT NULL,
  `des` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `species` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `breed` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(1) NOT NULL,
  `birth` date NOT NULL,
  `picture` varchar(255) CHARACTER SET latin1 NOT NULL DEFAULT 'http://192.168.82.102:88/demo_pets/pet_logo.png',
  `love` enum('true','false') CHARACTER SET latin1 NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `pet`
--

INSERT INTO `pet` (`id`, `des`, `name`, `species`, `breed`, `gender`, `birth`, `picture`, `love`) VALUES
(1, 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, con', 'Lucime', 'long ngan', 'meo anh', 1, '2019-10-17', '/demo_pets/pets_picture/1.jpg', 'false'),
(2, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has su', 'MeoMun', 'Mèo lông dài', 'Mèo em', 1, '2019-10-06', '/demo_pets/pets_picture/2.jpeg', 'false'),
(3, 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, yo', 'Taptap', 'Mèo lông dài', 'Mèo em', 1, '2019-10-01', '/demo_pets/pets_picture/6.jpg', 'false'),
(4, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'Booo', 'long ngan', 'meo anh', 1, '2019-10-02', '/demo_pets/pets_picture/7.jpg', 'true'),
(26, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'OMMO', 'long ngan', 'meo anh', 2, '2019-10-02', '/demo_pets/pets_picture/5.jpg', 'true'),
(27, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'Lorem', 'long ngan', 'meo anh', 2, '2019-10-02', '/demo_pets/pets_picture/8.jpg', 'true'),
(28, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'LOLIPOP', 'Lông cụt', 'Chó nhà', 2, '2019-10-02', '/demo_pets/pets_picture/9.jpg', 'true'),
(29, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'YouD', 'long ngan', 'meo anh', 2, '2019-10-02', '/demo_pets/pets_picture/20.jpeg', 'true'),
(30, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'Tity', 'long ngan', 'meo anh', 2, '2019-10-02', '/demo_pets/pets_picture/21.jpeg', 'true'),
(31, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'DAO', 'long ngan', 'meo anh', 2, '2019-10-02', '/demo_pets/pets_picture/22.jpeg', 'true'),
(33, 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions ', 'YouD', 'long ngan', 'meo anh', 2, '2019-10-02', '/demo_pets/pets_picture/24.jpeg', 'true');

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
('admin', '1234567', 'Hữu Mass', 'robotmeo@gmail.com', '0234567899999', '329 - Hà Nội ', 1, NULL, 'OmyGod@yahoo.com.vn', '20/09/1999'),
('anhhuu', '123456', 'DR.MONN', 'robotmeobg@gmail.com ', '0599853354', '', 0, NULL, 'OmyChuoi@yahoo.com.vn', '20/09/1999'),
('jonkey', '123', '', 'tongtien@gmail.com', '094965266585', '', 0, NULL, '', ''),
('member', '123456', 'Minh nuti', 'membermin@yahoo.com.vn', '012364987', '123 - TP.HCM', 0, NULL, 'Obama@feedback.com', '20/09/1999'),
('qwer', '123456', 'Kem tươi', 'rongbg@gmail.com ', '096845333', '', 0, NULL, '', '20/09/1999'),
('yyyyy', 'bnm', 'Lung Lo', 'hhh', '85668856', 'Hn', 0, NULL, 'iii8iiiiiiiiiiiiiiiiiiii', '27 October 2019');

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
  ADD KEY `user_id` (`user_id`),
  ADD KEY `user_bi_like` (`user_bi_like`),
  ADD KEY `baiviet_id` (`baiviet_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=130;

--
-- AUTO_INCREMENT cho bảng `noti`
--
ALTER TABLE `noti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `pet`
--
ALTER TABLE `pet`
  MODIFY `id` int(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

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

--
-- Các ràng buộc cho bảng `noti`
--
ALTER TABLE `noti`
  ADD CONSTRAINT `noti_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `noti_ibfk_2` FOREIGN KEY (`user_bi_like`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `noti_ibfk_3` FOREIGN KEY (`baiviet_id`) REFERENCES `baiviet` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
