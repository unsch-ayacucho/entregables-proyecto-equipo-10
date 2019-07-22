INSERT INTO `categorizacionbd`.`usuario`
(`idusuario`,
`usuario`,
`password`,
`cargo`)
VALUES
(1,
"roberto",
"$2a$10$3oVT1Y8I6InrgaQrHP/WOu7NYxs9DIA09hAdOtySEq1XKYLmYPgTS",
"Docente");

INSERT INTO `categorizacionbd`.`docente`
(`iddocente`,
`idusuario`,
`categoria`,
`nombres`,
`apepaterno`,
`apematerno`,
`nrodoc`,
`fecha_nacimiento`,
`domicilio`,
`celular`,
`sexo`)
VALUES
(1,
1,
"Asociado",
"Roberto",
"Villano",
"Salvador",
"45238979",
'1985-7-04',
"Urb la Julia SN",
"979666666",
"m");
