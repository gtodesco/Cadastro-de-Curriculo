import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CurriculooficialserviceService } from '../service/curriculooficialservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent{

  hide = true;
  cpf = "";
  password = "";

  form = this.fb.group({
    curriculo: this.fb.group({
      cpf: this.fb.control('', [Validators.required]),
      password: this.fb.control('', [Validators.required])
    })
  });

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
              private curriculoService: CurriculooficialserviceService) { }

  fazerLogin(){    
    this.curriculoService.getCurriculoPorCPF(this.cpf)
    .subscribe(
      res => {if(res != null){
        if(this.password === JSON.parse(JSON.stringify(res)).password){
          sessionStorage.setItem("cpf", this.cpf);
          this.router.navigate(['/cadastro']);
        }else{
          alert("Password incorreto");
        }
      }else{
        alert("CPF não cadastrado");
      }},
      error => console.log(error)
    );
  }

  cadastrarCurriculo(){
    sessionStorage.setItem("cpf", "null");
  }

  getRequiredMessage(name: string) {
    return `Você precisa digitar o ${name}`;
  }

}
