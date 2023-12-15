import { Component } from '@angular/core';
import { LabseqService } from '../../services/labseq.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-labseq',
  templateUrl: './labseq.component.html',
  styleUrl: './labseq.component.scss'
})
export class LabseqComponent {
  labseqIndex: number;
  labseqIndexResponse: number;
  labseqValue: any;
  LabseqForm: FormGroup;
  errorMessage : string = '';
  Error_bool : boolean = false;
  constructor(private labseqService:LabseqService){}

  ngOnInit(): void {
    this.LabseqForm = new FormGroup({
      labseqIndex: new FormControl('', Validators.required)
    });
  }

  getLabseqValue(): void {
    if (this.LabseqForm.valid) {
      this.labseqService.getLabseqValue(this.LabseqForm.value.labseqIndex)
        .then(value => {
          this.labseqValue = value;
          this.labseqIndexResponse = this.LabseqForm.value.labseqIndex;
          this.errorMessage = '';
          this.Error_bool = false;
        })
        .catch(error => {
          this.Error_bool = true;
          this.errorMessage = 'Error fetching labseq value';
        });
    }else{
      this.errorMessage = 'Please enter a valid index';
    }
  }
}
