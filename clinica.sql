-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 15-Jun-2019 às 18:42
-- Versão do servidor: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinica`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agendamentos`
--

CREATE TABLE `agendamentos` (
  `id` int(10) UNSIGNED NOT NULL,
  `idpaciente` int(11) NOT NULL,
  `paciente` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `area` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `procedimento` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hora` time NOT NULL,
  `data` date NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `entradas`
--

CREATE TABLE `entradas` (
  `id` int(10) UNSIGNED NOT NULL,
  `remedio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantidade` int(11) NOT NULL,
  `data` date NOT NULL,
  `hora` time NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `entradas`
--

INSERT INTO `entradas` (`id`, `remedio`, `quantidade`, `data`, `hora`, `created_at`, `updated_at`) VALUES
(27, 'amoxilina', 50, '2019-05-31', '15:31:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoques`
--

CREATE TABLE `estoques` (
  `id` int(10) UNSIGNED NOT NULL,
  `remedio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `categoria` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descricao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantidade` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `estoques`
--

INSERT INTO `estoques` (`id`, `remedio`, `categoria`, `descricao`, `quantidade`, `created_at`, `updated_at`) VALUES
(2, 'amoxilina', 'Atiflamatório', 'descrição', 318, '2019-03-14 15:56:15', '2019-03-14 15:56:15'),
(3, 'dipirona', 'Atiflamatório', 'dsfdfd', 29, '2019-03-14 16:15:49', '2019-03-14 16:16:22');

-- --------------------------------------------------------

--
-- Estrutura da tabela `logs`
--

CREATE TABLE `logs` (
  `id` int(10) UNSIGNED NOT NULL,
  `data` date NOT NULL,
  `hora` time NOT NULL,
  `titulo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `atividade` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `logs`
--

INSERT INTO `logs` (`id`, `data`, `hora`, `titulo`, `atividade`, `created_at`, `updated_at`) VALUES
(1, '2019-03-07', '13:35:01', 'Paciente Adicionado!', 'Adicionado o paciente  wesly ao sistema', '2019-03-07 16:35:01', '2019-03-07 16:35:01'),
(2, '2019-03-07', '13:36:01', 'Novo Agendamento!', 'O paciente  wesly fez um agendamento', '2019-03-07 16:36:01', '2019-03-07 16:36:01'),
(3, '2019-03-07', '13:36:19', 'Agendamento Alterado!', 'Agendamento id(1) Alterado', '2019-03-07 16:36:19', '2019-03-07 16:36:19'),
(4, '2019-03-07', '13:36:34', 'Agendamento Alterado!', 'Agendamento id(1) Alterado', '2019-03-07 16:36:34', '2019-03-07 16:36:34'),
(5, '2019-03-07', '14:22:29', 'Medicamento Cadastrado!', 'Medicamento dipirona adicionado ao sistema', '2019-03-07 17:22:29', '2019-03-07 17:22:29'),
(6, '2019-03-08', '10:32:25', 'Medicamento Cadastrado!', 'Medicamento Amoxilina adicionado ao sistema', '2019-03-08 13:32:25', '2019-03-08 13:32:25'),
(7, '2019-03-08', '10:32:45', 'Medicamento Alterado!', 'Medicamento Amoxilina alterado', '2019-03-08 13:32:45', '2019-03-08 13:32:45'),
(8, '2019-03-08', '10:33:14', 'Medicamento Removido!', 'Removido o medicamento Amoxilina do sistema', '2019-03-08 13:33:14', '2019-03-08 13:33:14'),
(9, '2019-03-08', '11:01:21', 'Medicamento Alterado!', 'Medicamento dipirona alterado', '2019-03-08 14:01:21', '2019-03-08 14:01:21'),
(10, '2019-03-08', '11:02:15', 'Medicamento Alterado!', 'Medicamento dipirona alterado', '2019-03-08 14:02:15', '2019-03-08 14:02:15'),
(11, '2019-03-11', '15:25:06', 'Agendamento Removido!', 'Agendamento para o paciente wesly Removido do sistema!', '2019-03-11 18:25:06', '2019-03-11 18:25:06'),
(12, '2019-03-13', '10:20:19', 'Paciente Removido!', 'Paciente wesly Removido(a) do sistema!', '2019-03-13 13:20:19', '2019-03-13 13:20:19'),
(13, '2019-03-13', '10:21:49', 'Paciente Adicionado!', 'Adicionado o paciente  MARCOS WESLY ALVES DOS SANTOS ao sistema', '2019-03-13 13:21:49', '2019-03-13 13:21:49'),
(14, '2019-03-13', '10:22:07', 'Novo Agendamento!', 'O paciente  MARCOS WESLY ALVES DOS SANTOS fez um agendamento', '2019-03-13 13:22:07', '2019-03-13 13:22:07'),
(15, '2019-03-13', '10:22:38', 'Agendamento Alterado!', 'Agendamento id(1) Alterado', '2019-03-13 13:22:38', '2019-03-13 13:22:38'),
(16, '2019-03-13', '10:40:29', 'Novo Agendamento!', 'O paciente  MARCOS WESLY ALVES DOS SANTOS fez um agendamento', '2019-03-13 13:40:29', '2019-03-13 13:40:29'),
(17, '2019-03-14', '12:39:41', 'Agendamento Removido!', 'Agendamento para o paciente MARCOS WESLY ALVES DOS SANTOS Removido do sistema!', '2019-03-14 15:39:41', '2019-03-14 15:39:41'),
(18, '2019-03-14', '12:40:33', 'Novo Agendamento!', 'O paciente  MARCOS WESLY ALVES DOS SANTOS fez um agendamento', '2019-03-14 15:40:33', '2019-03-14 15:40:33'),
(19, '2019-03-14', '12:55:39', 'Medicamento Removido!', 'Removido o medicamento dipirona do sistema', '2019-03-14 15:55:39', '2019-03-14 15:55:39'),
(20, '2019-03-14', '12:56:15', 'Medicamento Cadastrado!', 'Medicamento amoxilina adicionado ao sistema', '2019-03-14 15:56:15', '2019-03-14 15:56:15'),
(21, '2019-03-14', '13:01:59', 'Paciente Adicionado!', 'Adicionado o paciente  RAFAEL ABNE ao sistema', '2019-03-14 16:01:59', '2019-03-14 16:01:59'),
(22, '2019-03-14', '13:14:58', 'Novo Agendamento!', 'O paciente  RAFAEL ABNE fez um agendamento', '2019-03-14 16:14:58', '2019-03-14 16:14:58'),
(23, '2019-03-14', '13:15:49', 'Medicamento Cadastrado!', 'Medicamento dipirona adicionado ao sistema', '2019-03-14 16:15:49', '2019-03-14 16:15:49');

-- --------------------------------------------------------

--
-- Estrutura da tabela `medicamentos`
--

CREATE TABLE `medicamentos` (
  `id` int(10) UNSIGNED NOT NULL,
  `medicamento` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `categoria` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descricao` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `quantidade` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `medicamentos`
--

INSERT INTO `medicamentos` (`id`, `medicamento`, `categoria`, `descricao`, `quantidade`, `created_at`, `updated_at`) VALUES
(2, 'amoxilina', 'Anti-inflamatório', 'descrição', 0, '2019-03-14 15:56:15', '2019-03-14 15:56:15'),
(3, 'dipirona', 'Analgésico', 'dsfdfd', 0, '2019-03-14 16:15:49', '2019-03-14 16:15:49'),
(7, 'omeprazol', 'Outra', 'nao sei pra que serve', 0, NULL, NULL),
(8, 'rafael jadson', 'Antibiótico', 'gay', 0, NULL, NULL),
(9, 'IJKJO', 'Analgésico', 'jxksjd', 0, NULL, NULL),
(10, 'loratadina', 'Antibiótico', 'é um remédio', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2019_03_01_143224_create_paciente_table', 1),
(4, '2019_03_01_193403_create_agendamentos_table', 1),
(5, '2019_03_04_215228_create_logs_table', 1),
(6, '2019_03_05_152108_create_medicamentos_table', 1),
(7, '2019_03_06_114526_create_entradas_table', 1),
(8, '2019_03_06_114538_create_saidas_table', 1),
(9, '2019_03_06_131301_create_estoques_table', 1),
(10, '2019_03_08_111841_create_relatorio_saidas_table', 2),
(11, '2019_03_08_113122_create_relatorio_entradas_table', 2),
(12, '2019_03_09_113940_create_users_table', 3),
(13, '2019_03_13_153707_create_relatorio_entradas_table', 4),
(14, '2019_03_13_154001_create_relatorio_saidas_table', 4),
(15, '2019_03_14_140810_create_relatorio_consultas_table', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pacientes`
--

CREATE TABLE `pacientes` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome_paciente` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sexo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `data_nascimento` date NOT NULL,
  `idade` int(11) NOT NULL,
  `bairro` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cpf` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sus` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `telefone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `pacientes`
--

INSERT INTO `pacientes` (`id`, `nome_paciente`, `sexo`, `data_nascimento`, `idade`, `bairro`, `cpf`, `sus`, `telefone`, `created_at`, `updated_at`) VALUES
(10, 'Kainan Paiva da Silva', 'Masculino', '2001-07-06', 17, 'Edmilson Correia Vasconcelos', '075.843.843-58', '00000000000', '(88) 9 9443-8065', NULL, NULL),
(11, 'asdff', 'Masculino', '2001-02-02', 22, 'asdf', '000.000.000-00', '00000000000', '(99) 9 9999-9999', NULL, NULL),
(12, 'Kainan Paiva', 'Masculino', '1212-12-12', 17, 'KOKOKO', '222.222.222-22', '2222222222222', '(99) 9 9999-9999', NULL, NULL),
(13, 'breno almeida', 'Masculino', '2002-02-02', 17, 'cje', '043.344.445-39', '00000000000', '(00) 0 0000-0000', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `relatorio_consultas`
--

CREATE TABLE `relatorio_consultas` (
  `id` int(10) UNSIGNED NOT NULL,
  `idpaciente` int(11) NOT NULL,
  `paciente` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `procedimento` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `data` date NOT NULL,
  `hora` time NOT NULL,
  `bairro` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `relatorio_consultas`
--

INSERT INTO `relatorio_consultas` (`id`, `idpaciente`, `paciente`, `procedimento`, `data`, `hora`, `bairro`, `created_at`, `updated_at`) VALUES
(31, 10, 'Kainan Paiva da Silva', 'asdf', '2019-06-01', '14:14:00', 'Edmilson Correia Vasconcelos', NULL, NULL),
(32, 0, 'asdff', 'asdf', '2019-06-01', '14:22:00', 'asdf', NULL, NULL),
(33, 10, 'Kainan Paiva da Silva', 'asdf', '2019-06-01', '16:02:00', 'Edmilson Correia Vasconcelos', NULL, NULL),
(34, 0, 'asdff', 'asdf', '2019-06-01', '17:15:00', 'asdf', NULL, NULL),
(35, 0, 'Kainan Paiva da Silva', 'asdf', '2019-06-14', '09:27:00', 'Edmilson Correia Vasconcelos', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `relatorio_entradas`
--

CREATE TABLE `relatorio_entradas` (
  `id` int(10) UNSIGNED NOT NULL,
  `remedio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `data_entrada` date NOT NULL,
  `hora_entrada` time NOT NULL,
  `quantidade` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `relatorio_entradas`
--

INSERT INTO `relatorio_entradas` (`id`, `remedio`, `data_entrada`, `hora_entrada`, `quantidade`, `created_at`, `updated_at`) VALUES
(1, 'dipirona', '2019-03-13', '16:22:44', 3, '2019-03-13 19:22:44', '2019-03-13 19:22:44'),
(2, 'dipirona', '2019-03-13', '16:33:27', 4, '2019-03-13 19:33:27', '2019-03-13 19:33:27'),
(3, 'dipirona', '2019-03-14', '13:16:10', 3, '2019-03-14 16:16:10', '2019-03-14 16:16:10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `relatorio_saidas`
--

CREATE TABLE `relatorio_saidas` (
  `id` int(10) UNSIGNED NOT NULL,
  `remedio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `data_saida` date NOT NULL,
  `hora_saida` time NOT NULL,
  `quantidade` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `relatorio_saidas`
--

INSERT INTO `relatorio_saidas` (`id`, `remedio`, `data_saida`, `hora_saida`, `quantidade`, `created_at`, `updated_at`) VALUES
(1, 'dipirona', '2019-03-13', '16:25:43', 6, '2019-03-13 19:25:43', '2019-03-13 19:25:43'),
(2, 'dipirona', '2019-03-13', '16:26:39', 2, '2019-03-13 19:26:39', '2019-03-13 19:26:39'),
(3, 'dipirona', '2019-03-13', '16:33:49', 20, '2019-03-13 19:33:49', '2019-03-13 19:33:49'),
(4, 'dipirona', '2019-03-14', '13:16:22', 3, '2019-03-14 16:16:22', '2019-03-14 16:16:22');

-- --------------------------------------------------------

--
-- Estrutura da tabela `saidas`
--

CREATE TABLE `saidas` (
  `id` int(10) UNSIGNED NOT NULL,
  `remedio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantidade` int(11) NOT NULL,
  `data` date NOT NULL,
  `hora` time NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `saidas`
--

INSERT INTO `saidas` (`id`, `remedio`, `quantidade`, `data`, `hora`, `created_at`, `updated_at`) VALUES
(1, 'dipirona', 12, '2001-03-10', '14:23:33', '2019-03-07 17:23:33', '2019-03-07 17:23:33'),
(3, 'dipirona', 2, '2019-03-13', '16:26:39', '2019-03-13 19:26:39', '2019-03-13 19:26:39'),
(4, 'dipirona', 20, '2019-03-13', '16:33:49', '2019-03-13 19:33:49', '2019-03-13 19:33:49'),
(5, 'dipirona', 3, '2019-03-14', '13:16:22', '2019-03-14 16:16:22', '2019-03-14 16:16:22'),
(6, 'amoxilina', 9, '2019-04-01', '15:51:00', NULL, NULL),
(7, 'amoxilina', 4, '2019-04-01', '15:53:00', NULL, NULL),
(8, 'amoxilina', 5, '2019-04-24', '15:21:00', NULL, NULL),
(9, 'amoxilina', 3, '2019-04-24', '15:54:00', NULL, NULL),
(10, 'amoxilina', 2, '2019-04-24', '15:59:00', NULL, NULL),
(11, 'amoxilina', 2, '2019-04-24', '16:01:00', NULL, NULL),
(12, 'amoxilina', 2, '2019-04-24', '16:07:00', NULL, NULL),
(13, 'dipirona', 1, '2019-04-24', '16:07:00', NULL, NULL),
(14, 'dipirona', 1, '2019-04-26', '11:24:00', NULL, NULL),
(15, 'amoxilina', 1, '2019-04-26', '11:38:00', NULL, NULL),
(16, 'dipirona', 1, '2019-05-13', '15:30:00', NULL, NULL),
(17, 'amoxilina', 40, '2019-05-20', '16:16:00', NULL, NULL),
(18, 'dipirona', 50, '2019-05-31', '15:44:00', NULL, NULL),
(19, 'dipirona', 2, '2019-05-31', '15:51:00', NULL, NULL),
(20, 'dipirona', 2, '2019-05-31', '15:52:00', NULL, NULL),
(21, 'dipirona', 50, '2019-05-31', '15:53:00', NULL, NULL),
(22, 'amoxilina', 50, '2019-06-01', '14:31:00', NULL, NULL),
(23, 'amoxilina', 50, '2019-06-01', '15:07:00', NULL, NULL),
(24, 'dipirona', 50, '2019-06-01', '15:08:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `usuario` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `senha` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `usuario`, `senha`, `created_at`, `updated_at`) VALUES
(1, 'teste', 'teste', '2019-03-30 03:00:00', '2019-03-05 03:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `estoques`
--
ALTER TABLE `estoques`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `remedio` (`remedio`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `medicamentos`
--
ALTER TABLE `medicamentos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `medicamento` (`medicamento`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `relatorio_consultas`
--
ALTER TABLE `relatorio_consultas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `relatorio_entradas`
--
ALTER TABLE `relatorio_entradas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `relatorio_saidas`
--
ALTER TABLE `relatorio_saidas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `saidas`
--
ALTER TABLE `saidas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agendamentos`
--
ALTER TABLE `agendamentos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `entradas`
--
ALTER TABLE `entradas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `estoques`
--
ALTER TABLE `estoques`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `medicamentos`
--
ALTER TABLE `medicamentos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `relatorio_consultas`
--
ALTER TABLE `relatorio_consultas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `relatorio_entradas`
--
ALTER TABLE `relatorio_entradas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `relatorio_saidas`
--
ALTER TABLE `relatorio_saidas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `saidas`
--
ALTER TABLE `saidas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
