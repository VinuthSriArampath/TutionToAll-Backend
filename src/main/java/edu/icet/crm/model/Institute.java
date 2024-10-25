package edu.icet.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Institute {
    private String instituteId;
    private String name;
    private String email;
    private String contact;
    private String address;
}
