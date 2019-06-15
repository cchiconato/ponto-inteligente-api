CREATE TABLE empresa
(
  id bigint NOT NULL,
  cnpj character varying(255) NOT NULL,
  data_atualizacao timestamp without time zone NOT NULL,
  data_criacao timestamp without time zone NOT NULL,
  razao_social character varying(255) NOT NULL,
  CONSTRAINT empresa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE empresa
  OWNER TO postgres;
  
  
CREATE TABLE funcionario
(
  id bigint NOT NULL,
  cpf character varying(255),
  data_atualizacao timestamp without time zone,
  data_criacao timestamp without time zone,
  email character varying(255),
  nome character varying(255),
  perfil character varying(255),
  qtd_horas_almoco real NOT NULL,
  qtd_horas_trabalho_dia real NOT NULL,
  senha character varying(255),
  valor_hora numeric(19,2),
  empresa_id bigint,
  CONSTRAINT funcionario_pkey PRIMARY KEY (id),
  CONSTRAINT fk4cm1kg523jlopyexjbmi6y54j FOREIGN KEY (empresa_id)
      REFERENCES empresa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE funcionario
  OWNER TO postgres;
  
  
CREATE TABLE lancamento
(
  id bigint NOT NULL,
  data timestamp without time zone,
  data_atualizacao timestamp without time zone,
  data_criacao timestamp without time zone,
  descricao character varying(255),
  localizacao character varying(255),
  tipo character varying(255),
  funcionario_id bigint,
  CONSTRAINT lancamento_pkey PRIMARY KEY (id),
  CONSTRAINT fk46i4k5vl8wah7feutye9kbpi4 FOREIGN KEY (funcionario_id)
      REFERENCES funcionario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE lancamento
  OWNER TO postgres;
  
  
  
  
