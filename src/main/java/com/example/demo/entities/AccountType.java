package com.example.demo.entities;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "accounttype")
public class AccountType {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idaccountType;

        @Column(length = 45)
        private String accountTypeName;


        public AccountType() {
        }


    public AccountType( String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }



    public Integer getIdaccountType() {
        return idaccountType;
    }

    public void setIdaccountType(Integer idaccountType) {
        this.idaccountType = idaccountType;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(idaccountType, that.idaccountType) && Objects.equals(accountTypeName, that.accountTypeName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idaccountType);
        return hash;
    }

    @Override
    public String toString() {
        return
                 accountTypeName;
    }
}
