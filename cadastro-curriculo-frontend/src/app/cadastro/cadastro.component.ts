import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CurriculooficialserviceService } from '../service/curriculooficialservice.service';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  hide = true;
  nome;
  email;
  cpf;
  dataNascimento;
  cep;
  sexo = "Masculino";
  endereco;
  pais;
  escolaridade;
  formacao;
  experienciaProfissional;
  password;

  sexos: string[] = ['Masculino', 'Feminino'];

  form = this.fb.group({
    curriculo: this.fb.group({
      nome: this.fb.control('', [Validators.required]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      cpf: this.fb.control('', [Validators.required]),
      dataNascimento: this.fb.control('', [Validators.required]),
      cep: this.fb.control('', [Validators.required]),
      sexo: this.fb.control(''),
      endereco: this.fb.control('', [Validators.required]),
      pais: this.fb.control('', [Validators.required]),
      escolaridade: this.fb.control('', [Validators.required]),
      formacao: this.fb.control('', [Validators.required]),
      experienciaProfissional: this.fb.control('', [Validators.required]),
      password: this.fb.control('', [Validators.required])
    })
  });

  constructor(private fb: FormBuilder, private fb2: FormBuilder, private route: ActivatedRoute, private router: Router,
              private curriculoService: CurriculooficialserviceService) { }

  ngOnInit() {
    if(sessionStorage.getItem("cpf") != "null"){
      this.curriculoService.getCurriculoPorCPF(sessionStorage.getItem("cpf"))
      .subscribe(
        res => {
          let curriculo = this.getOjbJSON(res);
          this.nome = curriculo.nome;
          this.email = curriculo.email;
          this.cpf = curriculo.cpf;
          this.dataNascimento = curriculo.dataNascimento;
          this.cep = curriculo.cep;
          this.sexo = curriculo.sexo;
          this.escolaridade = curriculo.escolaridade;
          this.formacao = curriculo.formacao;
          this.experienciaProfissional = curriculo.experienciaProfissional;
          this.password = curriculo.password;
          this.getEnderecoPorCep();
        },
        error => console.log(error)
      );
    }
  }

  getOjbJSON(json: any){
    return JSON.parse(JSON.stringify(json));
  }

  getEnderecoPorCep(){
    this.curriculoService.getCEP(this.cep)
    .subscribe(
      res => {
        this.endereco = this.getOjbJSON(res).ds_endereco + ", " + this.getOjbJSON(res).ds_cidade + "-" + this.getOjbJSON(res).ds_uf;
        this.pais = this.getOjbJSON(res).ds_pais;
        console.log(this.getOjbJSON(res));
      },
      error => console.log(error)
    )
  }

  salvarDados(){
    if(sessionStorage.getItem("cpf") === "null"){
      console.log("SALVOU");
      //this.curriculoService.postCurriculo(this.form.value.curriculo).pipe(first()).subscribe();
      this.curriculoService.postCurriculo(this.form.value.curriculo).subscribe();
      this.router.navigate(['/login']);
    }else{
      console.log('ALTEROU');
      console.log(this.form.value.curriculo);
      this.curriculoService.getCurriculoPorCPF(sessionStorage.getItem("cpf"))
      .subscribe(
        res => {
          let novoFormulario = this.fb2.group({
            novoCurriculo: this.fb2.group({
              id: JSON.parse(JSON.stringify(res)).id,
              nome: this.nome,
              email: this.email,
              cpf: this.cpf,
              dataNascimento: this.dataNascimento,
              cep: this.cep,
              sexo: this.sexo,
              escolaridade: this.escolaridade,
              formacao: this.formacao,
              experienciaProfissional: this.experienciaProfissional,
              password: this.password
            })
          });
          console.log(novoFormulario.value.novoCurriculo);
          //this.curriculoService.putCurriculo(novoFormulario.value.novoCurriculo).pipe(first()).subscribe();  
          this.curriculoService.putCurriculo(novoFormulario.value.novoCurriculo).subscribe();  
          this.router.navigate(['/login']);
        },
        error => console.log(error)
      )
    }
  }

  sair(){
    sessionStorage.removeItem("cpf");
    this.router.navigate(['/login']);
  }

  getRequiredMessage(name: string) {
    return `Você precisa digitar o ${name}`;
  }

}
