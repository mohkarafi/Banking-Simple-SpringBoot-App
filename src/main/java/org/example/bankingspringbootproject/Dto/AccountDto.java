package org.example.bankingspringbootproject.Dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// une class record : est une class que toutes les attributes sont declarer comme final (immutable)
// au lieu de creer Constractor , Getter Setter , la class record remplace toutes ca .
public class AccountDto
{
    private   Long id ;
    private   String accountHolderName;
    private double balance;
};
