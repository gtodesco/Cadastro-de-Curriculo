import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { HttpHeaders } from '@angular/common/http';
import { Curriculo } from '../model/curriculo'

@Injectable({
  providedIn: 'root'
})
export class CurriculooficialserviceService {

  private readonly URL = "http://localhost:8080/api/curriculos";
  private readonly URL_cep = "http://unimestre.armazemdc.com.br/microservicos/cep/api/index.php?cep=";

  constructor(private http: HttpClient) { }

  getCurriculos(){
    return this.http.get<Curriculo[]>(this.URL);
  }

  getCurriculoPorID(id: number){
    return this.http.get(this.URL + '/' + id)
  }

  getCurriculoPorCPF(cpf: string){
    return this.http.get(this.URL + '/buscar?cpf=' + cpf)
  }

  getCEP(cep: string){
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization-Token': 'token-micro-cep',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get(this.URL_cep + cep, httpOptions)   
  }

  postCurriculo(curriculo: Curriculo){
    return this.http.post<Curriculo>(this.URL, curriculo);
  }

  putCurriculo(curriculo: Curriculo){
    return this.http.put<Curriculo>(this.URL, curriculo);
  }

}
