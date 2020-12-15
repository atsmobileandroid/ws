import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICountries, Countries } from 'app/shared/model/countries.model';
import { CountriesService } from './countries.service';

@Component({
  selector: 'jhi-countries-update',
  templateUrl: './countries-update.component.html',
})
export class CountriesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    countryNameEn: [],
    countryNameAr: [],
    flagImagePath: [],
    currencyNameEn: [],
    currencyNameAr: [],
    exchangeRate: [],
  });

  constructor(protected countriesService: CountriesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ countries }) => {
      this.updateForm(countries);
    });
  }

  updateForm(countries: ICountries): void {
    this.editForm.patchValue({
      id: countries.id,
      countryNameEn: countries.countryNameEn,
      countryNameAr: countries.countryNameAr,
      flagImagePath: countries.flagImagePath,
      currencyNameEn: countries.currencyNameEn,
      currencyNameAr: countries.currencyNameAr,
      exchangeRate: countries.exchangeRate,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const countries = this.createFromForm();
    if (countries.id !== undefined) {
      this.subscribeToSaveResponse(this.countriesService.update(countries));
    } else {
      this.subscribeToSaveResponse(this.countriesService.create(countries));
    }
  }

  private createFromForm(): ICountries {
    return {
      ...new Countries(),
      id: this.editForm.get(['id'])!.value,
      countryNameEn: this.editForm.get(['countryNameEn'])!.value,
      countryNameAr: this.editForm.get(['countryNameAr'])!.value,
      flagImagePath: this.editForm.get(['flagImagePath'])!.value,
      currencyNameEn: this.editForm.get(['currencyNameEn'])!.value,
      currencyNameAr: this.editForm.get(['currencyNameAr'])!.value,
      exchangeRate: this.editForm.get(['exchangeRate'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICountries>>): void {
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
