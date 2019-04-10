package io.eblock.eos4j.api.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dyz on 2018/12/28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalResources {
     private String cpu_weight;
     private String net_weight;
     private String owner;
     private long ram_bytes;

     public String getCpu_weight() {
          return cpu_weight;
     }

     public void setCpu_weight(String cpu_weight) {
          this.cpu_weight = cpu_weight;
     }

     public String getNet_weight() {
          return net_weight;
     }

     public void setNet_weight(String net_weight) {
          this.net_weight = net_weight;
     }

     public String getOwner() {
          return owner;
     }

     public void setOwner(String owner) {
          this.owner = owner;
     }

     public long getRam_bytes() {
          return ram_bytes;
     }

     public void setRam_bytes(long ram_bytes) {
          this.ram_bytes = ram_bytes;
     }

     @Override
     public String toString() {
          return "TotalResources{" +
                  "cpu_weight='" + cpu_weight + '\'' +
                  ", net_weight='" + net_weight + '\'' +
                  ", owner='" + owner + '\'' +
                  ", ram_bytes=" + ram_bytes +
                  '}';
     }
}
