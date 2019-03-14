import { MatSnackBar } from '@angular/material';
import { Curriculo } from '../model/curriculo';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

const HOST = 'http://localhost:8080';

@Injectable()
export class CurriculoService {

  constructor(
    private http: HttpClient
  ) {}

  getAllCurriculos() {
    return this.http.get<Curriculo[]>(`${HOST}/api/curriculos`);
  }

  getById(id: number) {
    return this.http.get(`${HOST}/api/curriculos/${id}`);
  }

  getByCpf(cpf: string) {
    return this.http.get(`${HOST}/api/curriculos/buscar?cpf=${cpf}`);
  }

  save(curriculo: any, type: string) {
    const newCurriculo = {
      nome: curriculo.nome,
      email: curriculo.email,
      cpf: curriculo.cpf,
      dataNascimento: curriculo.dataNascimento,
      cep: curriculo.cep,
      sexo: curriculo.sexo,
      escolaridade: curriculo.escolaridade,
      formacao: curriculo.formacao,
      experienciaProfissional: curriculo.experienciaProfissional,
      password: curriculo.password,
      type
    };

    return this.http.post(`${HOST}/api/curriculos`, newCurriculo);
  }

  update(curriculo: any) {

    let curriculoBuscado: any;

    curriculoBuscado = this.getByCpf(curriculo.cpf);

    curriculoBuscado.cpf = curriculo.cpf;

    return this.http.put(`${HOST}/curriculos`, curriculo);
  }

  delete(id: number) {
    return this.http.delete('${HOST}/api/curriculos/${id}');
  }
}
