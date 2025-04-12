package org.example.bankingprojecttest.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id ;
    private String AccountHolderName;
    private Double balance ;
}
