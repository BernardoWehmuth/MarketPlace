package edu.marketplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
	@NotBlank(message = "O usuário é obrigatório")
	private String usuario;
	
	@NotBlank(message = "o nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 8, message = "A senha deve possuir ao menos 8 caracteres")
	private String senha;
	
	@Email(message = "Email inválido")
	@NotBlank(message = "o email é obrigatório")
	private String email;
}
