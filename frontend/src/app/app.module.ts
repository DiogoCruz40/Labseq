import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LabseqComponent } from './components/labseq/labseq.component';
import { APIClientService } from './services/apiclient.service';
import { LabseqService } from './services/labseq.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LabseqComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [APIClientService, LabseqService],
  bootstrap: [AppComponent]
})
export class AppModule { }
