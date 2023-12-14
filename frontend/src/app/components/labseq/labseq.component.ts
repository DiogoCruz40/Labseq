import { Component } from '@angular/core';
import { LabseqService } from '../../services/labseq.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { APIClientService } from '../../services/apiclient.service';

@Component({
  selector: 'app-labseq',
  templateUrl: './labseq.component.html',
  styleUrl: './labseq.component.scss'
})
export class LabseqComponent {

  labseqIndex: number;
  labseqValue: any;

  constructor(private labseqService:LabseqService){}

  getLabseqValue(): void {
    if (this.labseqIndex) {
      this.labseqService.getLabseqValue(this.labseqIndex)
        .then(value => {
          this.labseqValue = value;
        })
        .catch(error => {
          console.error('Error fetching labseq value:', error);
        });
    }
  }
}
