import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILevels } from 'app/shared/model/levels.model';

@Component({
  selector: 'jhi-levels-detail',
  templateUrl: './levels-detail.component.html',
})
export class LevelsDetailComponent implements OnInit {
  levels: ILevels | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ levels }) => (this.levels = levels));
  }

  previousState(): void {
    window.history.back();
  }
}
