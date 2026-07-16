--liquibase formatted sql

--changeset author:create_life_policies_table
CREATE TABLE life_insurance_policies (
                                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                         policy_number VARCHAR(50) NOT NULL UNIQUE,
                                         client_external_id UUID NOT NULL,
                                         start_date DATE NOT NULL,
                                         end_date DATE NOT NULL,
                                         status VARCHAR(20) NOT NULL CHECK (status IN ('DRAFT', 'ACTIVE', 'EXPIRED', 'CANCELLED', 'SUSPENDED')),
                                         premium_amount DECIMAL(15,2),
                                         coverage_amount DECIMAL(15,2),
                                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         insured_age INTEGER,
                                         beneficiary_name VARCHAR(200),
                                         beneficiary_relation VARCHAR(50),
                                         policy_term_years INTEGER,
                                         is_high_risk_occupation BOOLEAN DEFAULT false,
                                         has_dangerous_hobbies BOOLEAN DEFAULT false,
                                         coverage_type VARCHAR(50)
);