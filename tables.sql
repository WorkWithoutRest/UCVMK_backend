CREATE TABLE `Courses` (
  `id` integer,
  `title` varchar,
  `meta_d` varchar,
  `meta_k` integer,
  `text` text,
  `date_start` date,
  `date_finish` date,
  `status` boolean,
  constraint pk_cour primary key(id)
);

CREATE TABLE `Login/password` (
  `login` varchar,
  `password` varchar,
  constraint pk_logpass primary key(login, password)
);

CREATE TABLE `Course/teacher` (
  `course_id` integer,
  `teacher_id` integer,
  constraint fk_cour foreign key(cource_id) references Courses(id)
);

CREATE TABLE `Teachers` (
  `id` int,
  `name` varchar,
  `link` varchar,
  `login` varchar
);

CREATE TABLE `News` (
  `id` integer,
  `date` date,
  `text` text
);

CREATE TABLE `Clients` (
  `id` integer,
  `login` varchar,
  `password` varchar,
  `name` varchar,
  `surname` varchar,
  `patronymic_name` varchar,
  `birth` date,
  `email` varchar,
  `city` varchar,
  `street` varchar,
  `house` varchar,
  `apartment` varchar,
  `private_telephone` bigint
);

CREATE TABLE `Employees` (
  `id` int,
  `login` varchar
);

CREATE TABLE `Course/client` (
  `id_cource` int,
  `id_client` int
);

CREATE TABLE `Settings` (
  `id` int,
  `page_name` varchar,
  `title` varchar,
  `meta_d` varchar,
  `meta_k` varchar,
  `text` text
);

