-- sql script for schema
CREATE DATABASE IF NOT EXISTS SmartEstateManagementSystem;
USE SmartEstateManagementSystem;
CREATE TABLE customer (
  cnic               VARCHAR(15)      NOT NULL,
  f_name             VARCHAR(50)      NOT NULL,
  l_name             VARCHAR(50)      NOT NULL,
  customer_address   VARCHAR(255),
  no_of_plots        INT              DEFAULT 0,
  registration_date  DATE,
  CONSTRAINT customer_pk PRIMARY KEY (cnic)
);

CREATE TABLE plot (
  plot_no       INT               NOT NULL,
  area          DECIMAL(10,2),
  price         DECIMAL(12,2),
  type          ENUM('corner','main road','normal'),
  status        ENUM('owned','unowned','incomplete'),
  plot_address  VARCHAR(255),
  CONSTRAINT plot_pk PRIMARY KEY (plot_no)
);

CREATE TABLE owns (
  cnic      VARCHAR(15) NOT NULL,
  plot_no   INT         NOT NULL,
  CONSTRAINT owns_pk PRIMARY KEY (cnic, plot_no),
  CONSTRAINT owns_cnic_fk     FOREIGN KEY (cnic)     REFERENCES customer (cnic),
  CONSTRAINT owns_plot_no_fk  FOREIGN KEY (plot_no)  REFERENCES plot     (plot_no)
);

CREATE TABLE contacts (
  cnic      VARCHAR(15) NOT NULL,
  phone_no  VARCHAR(20) NOT NULL,
  CONSTRAINT contacts_pk PRIMARY KEY (cnic, phone_no),
  CONSTRAINT contacts_cnic_fk FOREIGN KEY (cnic) REFERENCES customer (cnic)
);

CREATE TABLE plot_transfer (
  plot_no       INT         NOT NULL,
  owner_no      VARCHAR(15) NOT NULL,  -- e.g. current owner’s CNIC
  buyer_cnic    VARCHAR(15) NOT NULL,
  seller_cnic   VARCHAR(15) NOT NULL,
  transfer_date DATE        NOT NULL,
  CONSTRAINT plot_transfer_pk PRIMARY KEY (plot_no, transfer_date),
  CONSTRAINT plot_transfer_plot_no_fk    FOREIGN KEY (plot_no)      REFERENCES plot     (plot_no),
  CONSTRAINT plot_transfer_owner_no_fk   FOREIGN KEY (owner_no)     REFERENCES customer (cnic),
  CONSTRAINT plot_transfer_buyer_cnic_fk FOREIGN KEY (buyer_cnic)   REFERENCES customer (cnic),
  CONSTRAINT plot_transfer_seller_cnic_fk FOREIGN KEY (seller_cnic) REFERENCES customer (cnic)
);

CREATE TABLE plot_return (
  return_id      INT        NOT NULL AUTO_INCREMENT,
  plot_no        INT        NOT NULL,
  returned_amount DECIMAL(12,2),
  return_date    DATE       NOT NULL,
  CONSTRAINT plot_return_pk PRIMARY KEY (return_id),
  CONSTRAINT plot_return_plot_no_fk FOREIGN KEY (plot_no) REFERENCES plot (plot_no)
);

CREATE TABLE payment (
  payment_id     INT        NOT NULL AUTO_INCREMENT,
  amount         DECIMAL(12,2),
  receiving_date DATE       NOT NULL,
  plot_no        INT        NOT NULL,
  CONSTRAINT payment_pk PRIMARY KEY (payment_id),
  CONSTRAINT payment_plot_no_fk FOREIGN KEY (plot_no) REFERENCES plot (plot_no)
);

CREATE TABLE advance (
  payment_id  INT NOT NULL,
  CONSTRAINT advance_pk PRIMARY KEY (payment_id),
  CONSTRAINT advance_payment_fk FOREIGN KEY (payment_id) REFERENCES payment (payment_id)
);

CREATE TABLE installment (
  installment_no INT NOT NULL AUTO_INCREMENT,
  payment_id     INT NOT NULL,
  CONSTRAINT installment_pk PRIMARY KEY (installment_no),
  CONSTRAINT installment_payment_fk FOREIGN KEY (payment_id) REFERENCES payment (payment_id)
);

CREATE TABLE installment_details (
  plot_no        INT    NOT NULL,
  installment_no INT    NOT NULL,
  due_date       DATE   NOT NULL,
  late_status    ENUM('paid','unpaid','latePaid','lateUnpaid','partialPaid'),
  CONSTRAINT installment_details_pk PRIMARY KEY (plot_no, installment_no),
  CONSTRAINT installment_details_plot_no_fk       FOREIGN KEY (plot_no)        REFERENCES plot        (plot_no),
  CONSTRAINT installment_details_installment_no_fk FOREIGN KEY (installment_no) REFERENCES installment (installment_no)
);

CREATE TABLE plan (
  plot_no          INT         NOT NULL,
  installment_plan VARCHAR(255),
  CONSTRAINT plan_pk PRIMARY KEY (plot_no),
  CONSTRAINT plan_plot_no_fk FOREIGN KEY (plot_no) REFERENCES plot (plot_no)
);

CREATE TABLE fine (
  plot_no        INT         NOT NULL,
  installment_no INT         NOT NULL,
  short_amount   DECIMAL(12,2),
  fine_percentage DECIMAL(5,2),
  CONSTRAINT fine_pk PRIMARY KEY (plot_no, installment_no),
  CONSTRAINT fine_plot_no_fk        FOREIGN KEY (plot_no)        REFERENCES plot        (plot_no),
  CONSTRAINT fine_installment_no_fk FOREIGN KEY (installment_no) REFERENCES installment (installment_no)
);

CREATE TABLE heads (
  head VARCHAR(50) NOT NULL,
  type ENUM('income','expense') NOT NULL,
  CONSTRAINT heads_pk PRIMARY KEY (head)
);

CREATE TABLE expenses (
  expense_id INT        NOT NULL AUTO_INCREMENT,
  date       DATE       NOT NULL,
  head       VARCHAR(50) NOT NULL,
  amount     DECIMAL(12,2),
  CONSTRAINT expenses_pk PRIMARY KEY (expense_id),
  CONSTRAINT expenses_head_fk FOREIGN KEY (head) REFERENCES heads (head)
);

CREATE TABLE income (
  income_id INT        NOT NULL AUTO_INCREMENT,
  date      DATE       NOT NULL,
  head      VARCHAR(50) NOT NULL,
  amount    DECIMAL(12,2),
  CONSTRAINT income_pk PRIMARY KEY (income_id),
  CONSTRAINT income_head_fk FOREIGN KEY (head) REFERENCES heads (head)
);
