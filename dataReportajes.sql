INSERT INTO Agencia (id_agencia, nombre) VALUES (1, 'Agencia Central de Noticias');
INSERT INTO Agencia (id_agencia, nombre) VALUES (2, 'Prensa Global');
INSERT INTO Agencia (id_agencia, nombre) VALUES (3, 'Agencia EFE Norte');
INSERT INTO Agencia (id_agencia, nombre) VALUES (4, 'Asturias Media');

INSERT INTO Empresa_Comunicacion (id_empresa, nombre, email) VALUES (1, 'Diario El Mañana', 'redaccion@elmanana.com');
INSERT INTO Empresa_Comunicacion (id_empresa, nombre, email) VALUES (2, 'Canal 24 Horas', 'noticias@canal24.tv');
INSERT INTO Empresa_Comunicacion (id_empresa, nombre, email) VALUES (3, 'Radio Gijón', 'contacto@radiogijon.es');
INSERT INTO Empresa_Comunicacion (id_empresa, nombre, email) VALUES (4, 'La Nueva España', 'lne@redaccion.es');
INSERT INTO Empresa_Comunicacion (id_empresa, nombre, email) VALUES (5, 'TeleAsturias', 'informativos@teleasturias.tv');
INSERT INTO Empresa_Comunicacion (id_empresa, nombre, email) VALUES (6, 'El Comercio', 'noticias@elcomercio.es');

INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (1, 'Ana Blanco', '600111222', 1);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (2, 'Matias Prats', '600333444', 1);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (3, 'Lois Lane', '600555666', 2);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (4, 'Clark Kent', '600777888', 2);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (5, 'Peter Parker', '600999000', 3);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (6, 'Eddie Brock', '600123456', 3);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (7, 'Sara Carbonero', '600654321', 4);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (8, 'Iñaki Gabilondo', '600987654', 4);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (9, 'Julia Otero', '600112233', 1);
INSERT INTO Reportero (id_reportero, nombre, telefono, id_agencia) VALUES (10, 'Jordi Evole', '600445566', 2);

INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (1, 'Rueda de prensa del Alcalde', '2026-03-10', 1);
INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (2, 'Manifestacion por el clima', '2026-03-15', 2);
INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (3, 'Inauguracion del nuevo hospital', '2026-04-02', 1);
INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (4, 'Derbi asturiano en El Molinon', '2026-04-15', 4);
INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (5, 'Concierto en Laboral Ciudad de la Cultura', '2026-05-20', 4);
INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (6, 'Feria Internacional de Muestras (FIDMA)', '2026-08-01', 3);
INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (7, 'Festival Internacional de Cine de Gijon', '2026-11-15', 3);
INSERT INTO Evento (id_evento, descripcion, fecha, id_agencia) VALUES (8, 'Huelga del sector del metal', '2026-03-22', 2);




INSERT INTO Asignacion (id_evento, id_reportero) VALUES (1, 1);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (1, 9);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (2, 3);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (2, 10);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (3, 2);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (4, 7);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (4, 8);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (5, 7);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (6, 5);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (7, 6);
INSERT INTO Asignacion (id_evento, id_reportero) VALUES (8, 4);

INSERT INTO Reportaje (id_reportaje, id_evento, id_reportero, titulo, subtitulo, cuerpo, fecha_entrega) VALUES (1, 1, 1, 'El Alcalde promete nuevas ayudas', 'Se destinarán 5 millones', 'Durante la mañana de hoy, el alcalde ha prometido en rueda de prensa...', '2026-03-11 10:30:00');
INSERT INTO Reportaje (id_reportaje, id_evento, id_reportero, titulo, subtitulo, cuerpo, fecha_entrega) VALUES (2, 2, 3, 'Multitudinaria marcha ecologista', 'Miles de personas toman las calles', 'La manifestación comenzó a las 12:00 de forma pacífica...', '2026-03-16 09:15:00');
INSERT INTO Reportaje (id_reportaje, id_evento, id_reportero, titulo, subtitulo, cuerpo, fecha_entrega) VALUES (3, 4, 8, 'Empate sin goles en el derbi', 'Mucha tension y poco futbol', 'El partido disputado en El Molinón terminó con reparto de puntos...', '2026-04-16 08:00:00');
INSERT INTO Reportaje (id_reportaje, id_evento, id_reportero, titulo, subtitulo, cuerpo, fecha_entrega) VALUES (4, 6, 5, 'Record de asistencia en la Feria', 'Mas de 100.000 visitantes', 'El recinto ferial Luis Adaro cerró sus puertas con cifras históricas...', '2026-08-02 12:00:00');
INSERT INTO Reportaje (id_reportaje, id_evento, id_reportero, titulo, subtitulo, cuerpo, fecha_entrega) VALUES (5, 8, 4, 'Seguimiento masivo en la huelga', 'Los sindicatos celebran el exito', 'Las fábricas amanecieron paradas en la primera jornada de paros...', '2026-03-23 11:45:00');

INSERT INTO Version_Reportaje (id_version, id_reportaje, fecha_cambio, hora_cambio, subtitulo_guardado, cuerpo_guardado) VALUES (1, 1, '2026-03-10', '18:00:00', 'Borrador preliminar', 'Texto en redaccion por parte de Ana...');
INSERT INTO Version_Reportaje (id_version, id_reportaje, fecha_cambio, hora_cambio, subtitulo_guardado, cuerpo_guardado) VALUES (2, 1, '2026-03-11', '09:00:00', 'Correccion de cifras', 'Durante la mañana de hoy, el alcalde prometió...');
INSERT INTO Version_Reportaje (id_version, id_reportaje, fecha_cambio, hora_cambio, subtitulo_guardado, cuerpo_guardado) VALUES (3, 3, '2026-04-15', '20:30:00', 'Cronica primera parte', 'Primer tiempo aburrido en el estadio...');
INSERT INTO Version_Reportaje (id_version, id_reportaje, fecha_cambio, hora_cambio, subtitulo_guardado, cuerpo_guardado) VALUES (4, 4, '2026-08-01', '19:00:00', 'Datos iniciales', 'Primeros recuentos de visitantes de la FIDMA...');

INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (1, 1, 'ACEPTADO', false);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (1, 2, 'PENDIENTE', false);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (2, 3, 'RECHAZADO', false);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (4, 4, 'ACEPTADO', true);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (4, 5, 'ACEPTADO', true);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (4, 6, 'PENDIENTE', false);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (6, 6, 'ACEPTADO', true);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (6, 1, 'RECHAZADO', false);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (7, 5, 'PENDIENTE', false);
INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (8, 2, 'ACEPTADO', true);