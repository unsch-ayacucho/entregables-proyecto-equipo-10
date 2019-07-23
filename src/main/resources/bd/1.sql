INSERT INTO `categorizacionbd`.`usuario`
(`idusuario`,
`usuario`,
`password`,
`cargo`)
VALUES
(1,
"roberto",
"$2a$10$3oVT1Y8I6InrgaQrHP/WOu7NYxs9DIA09hAdOtySEq1XKYLmYPgTS",
"Docente")

;

INSERT INTO `categorizacionbd`.`historial`
(`idhistorial`,
`idusuario`,
`detalle`,
`fecha`)
VALUES
(1,
1,
"Usuario <roberto> creado.",
NOW())

;

INSERT INTO `categorizacionbd`.`categoria`
(`idcategoria`,
`nombre`)
VALUES
(1,
'Auxiliar');

INSERT INTO `categorizacionbd`.`categoria`
(`idcategoria`,
`nombre`)
VALUES
(2,
'Asociado');

INSERT INTO `categorizacionbd`.`categoria`
(`idcategoria`,
`nombre`)
VALUES
(3,
'Principal');


INSERT INTO `categorizacionbd`.`docente`
(`iddocente`,
`idusuario`,
`idcategoria`,
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
2,
"Roberto",
"Villano",
"Salvador",
"45238979",
'1985-7-04',
"Urb la Julia SN",
"979666666",
"m")

;

INSERT INTO `categorizacionbd`.`historial`
(`idhistorial`,
`idusuario`,
`detalle`,
`fecha`)
VALUES
(2,
1,
"Docente de nombre <Roberto Villano Salvador> creado.",
NOW())

;