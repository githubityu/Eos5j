package io.eblock;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Map;

/**
 * author: yu.jl
 * e-mail: bbfx89625@gmail.com
 * time  : 2019/4/10
 * desc  :
 */
public class Issue2245Test {
    /**
     * Customer
     */
    public static class Customer {

        private Long id;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
    }

    /**
     * Bar
     */
    public static class Bar {

        private Long id;
        private Customer customer;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Customer.class)
        @JsonIdentityReference(alwaysAsId = true)
        public Customer getCustomer() {
            return customer;
        }
        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
    }

    /**
     * Model
     */
    public static class Model {
        private Map<Long, Customer> customers;

        private Bar bar;

        public Map<Long, Customer> getCustomers() {
            return customers;
        }
        public void setCustomers(Map<Long, Customer> customers) {
            this.customers = customers;
        }


        public Bar getBar() {
            return bar;
        }

        public void setBar(final Bar bar) {
            this.bar = bar;
        }
    }

}
