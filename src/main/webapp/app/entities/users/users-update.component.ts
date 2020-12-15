import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IUsers, Users } from 'app/shared/model/users.model';
import { UsersService } from './users.service';
import { IBasket } from 'app/shared/model/basket.model';
import { BasketService } from 'app/entities/basket/basket.service';
import { ICountries } from 'app/shared/model/countries.model';
import { CountriesService } from 'app/entities/countries/countries.service';
import { IUserType } from 'app/shared/model/user-type.model';
import { UserTypeService } from 'app/entities/user-type/user-type.service';

type SelectableEntity = IBasket | ICountries | IUserType;

@Component({
  selector: 'jhi-users-update',
  templateUrl: './users-update.component.html',
})
export class UsersUpdateComponent implements OnInit {
  isSaving = false;
  baskets: IBasket[] = [];
  countries: ICountries[] = [];
  usertypes: IUserType[] = [];

  editForm = this.fb.group({
    id: [],
    email: [],
    password: [],
    fullName: [],
    imagePath: [],
    phoneNumber: [],
    createdDate: [],
    isActive: [],
    deviceType: [],
    deviceToken: [],
    balance: [],
    basket: [],
    countries: [],
    userType: [],
  });

  constructor(
    protected usersService: UsersService,
    protected basketService: BasketService,
    protected countriesService: CountriesService,
    protected userTypeService: UserTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ users }) => {
      this.updateForm(users);

      this.basketService
        .query({ filter: 'users-is-null' })
        .pipe(
          map((res: HttpResponse<IBasket[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IBasket[]) => {
          if (!users.basket || !users.basket.id) {
            this.baskets = resBody;
          } else {
            this.basketService
              .find(users.basket.id)
              .pipe(
                map((subRes: HttpResponse<IBasket>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IBasket[]) => (this.baskets = concatRes));
          }
        });

      this.countriesService.query().subscribe((res: HttpResponse<ICountries[]>) => (this.countries = res.body || []));

      this.userTypeService.query().subscribe((res: HttpResponse<IUserType[]>) => (this.usertypes = res.body || []));
    });
  }

  updateForm(users: IUsers): void {
    this.editForm.patchValue({
      id: users.id,
      email: users.email,
      password: users.password,
      fullName: users.fullName,
      imagePath: users.imagePath,
      phoneNumber: users.phoneNumber,
      createdDate: users.createdDate,
      isActive: users.isActive,
      deviceType: users.deviceType,
      deviceToken: users.deviceToken,
      balance: users.balance,
      basket: users.basket,
      countries: users.countries,
      userType: users.userType,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const users = this.createFromForm();
    if (users.id !== undefined) {
      this.subscribeToSaveResponse(this.usersService.update(users));
    } else {
      this.subscribeToSaveResponse(this.usersService.create(users));
    }
  }

  private createFromForm(): IUsers {
    return {
      ...new Users(),
      id: this.editForm.get(['id'])!.value,
      email: this.editForm.get(['email'])!.value,
      password: this.editForm.get(['password'])!.value,
      fullName: this.editForm.get(['fullName'])!.value,
      imagePath: this.editForm.get(['imagePath'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      deviceType: this.editForm.get(['deviceType'])!.value,
      deviceToken: this.editForm.get(['deviceToken'])!.value,
      balance: this.editForm.get(['balance'])!.value,
      basket: this.editForm.get(['basket'])!.value,
      countries: this.editForm.get(['countries'])!.value,
      userType: this.editForm.get(['userType'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsers>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
