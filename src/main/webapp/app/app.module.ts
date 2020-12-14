import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CardoholicSharedModule } from 'app/shared/shared.module';
import { CardoholicCoreModule } from 'app/core/core.module';
import { CardoholicAppRoutingModule } from './app-routing.module';
import { CardoholicHomeModule } from './home/home.module';
import { CardoholicEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CardoholicSharedModule,
    CardoholicCoreModule,
    CardoholicHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CardoholicEntityModule,
    CardoholicAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class CardoholicAppModule {}
