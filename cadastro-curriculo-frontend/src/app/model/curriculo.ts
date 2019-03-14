export class Curriculo {
  public id: number;
  public nome: string;
  public email: string;
  public cpf: string;
  public dataNascimento: string;
  public cep: string;
  public sexo: string;
  public escolaridade: string;
  public formacao: string;
  public experienciaProfissional: string;
  public password: string;

  constructor(id: number, nome: string, email: string, cpf: string, dataNascimento: string, cep: string, sexo: string, escolaridade: string, 
              formacao: string, experienciaProfissional: string, password: string){

    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.dataNascimento = dataNascimento;
    this.cep = cep;
    this.sexo = sexo;
    this.escolaridade = escolaridade;
    this.formacao = formacao;
    this.experienciaProfissional = experienciaProfissional;
    this.password = password;  
  }

}
    