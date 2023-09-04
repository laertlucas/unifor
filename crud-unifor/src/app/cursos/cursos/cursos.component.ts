import { Component, OnInit } from '@angular/core';
import { Curso } from '../model/curso';
import { CursosService } from '../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent implements OnInit {

  cursos: Curso[] = [];
  columnsToDisplay = ['_id', 'nome', 'categoria'];

  constructor(private cursosService: CursosService) {
    this.cursos = this.cursosService.list();
  }

  ngOnInit(): void {}

}
