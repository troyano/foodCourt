package com.pragma.foodcourt.application.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropietarioRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellido;

	@NotBlank
	@Size(max = 10, message = "El documento no puede superar los 10 caracteres")
	@Pattern(regexp = "\\d+", message = "El documento debe ser numérico")
	private String documentoIdentidad;

	@NotBlank
	@Size(max = 13, message = "El celular no puede superar los 13 caracteres")
	@Pattern(regexp = "^\\+?\\d{1,13}$", message = "El celular solo puede contener números y opcionalmente el símbolo +")
	private String celular;

	@NotNull
	private LocalDate fechaNacimiento;

	@NotBlank
	@Email(message = "Correo inválido")
	private String correo;

	@NotBlank
	private String clave;
}
