import { Injectable } from '@angular/core';
import { APIClientService } from './apiclient.service';


const LABSEQ_API = 'labseq';


@Injectable({
  providedIn: 'root'
})
export class LabseqService {

  constructor(private apiClient: APIClientService) {
  }

    getLabseqValue(labseqindex: number): Promise<any> {
      return this.apiClient.get(LABSEQ_API + '/' + labseqindex);
   }

  }