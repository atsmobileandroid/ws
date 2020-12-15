import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILevels, Levels } from 'app/shared/model/levels.model';
import { LevelsService } from './levels.service';

@Component({
  selector: 'jhi-levels-update',
  templateUrl: './levels-update.component.html',
})
export class LevelsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    title: [],
    color: [],
  });

  constructor(protected levelsService: LevelsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ levels }) => {
      this.updateForm(levels);
    });
  }

  updateForm(levels: ILevels): void {
    this.editForm.patchValue({
      id: levels.id,
      title: levels.title,
      color: levels.color,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const levels = this.createFromForm();
    if (levels.id !== undefined) {
      this.subscribeToSaveResponse(this.levelsService.update(levels));
    } else {
      this.subscribeToSaveResponse(this.levelsService.create(levels));
    }
  }

  private createFromForm(): ILevels {
    return {
      ...new Levels(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      color: this.editForm.get(['color'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevels>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
