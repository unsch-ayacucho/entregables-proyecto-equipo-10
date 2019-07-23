INSERT INTO `categorizacionbd`.`calendario`
(`idcalendario`,
`proceso`,
`fecha_inicio`,
`fecha_fin`)
VALUES
(1,
"Categorización 2019",
'2019-07-20',
'2019-07-27');

INSERT INTO `categorizacionbd`.`calendariodetalle`
(`idcalendariodetalle`,
`idcalendario`,
`actividad`,
`descripcion`)
VALUES
(1,
1,
"Proceso anual de categorización docente 2019",
"La categorización es un proceso académico - administrativo mediante el cual el docente ordinario es promovido a la categoría inmediata superior previa evaluación de sus méritos y conservando su régimen de dedicación.");
