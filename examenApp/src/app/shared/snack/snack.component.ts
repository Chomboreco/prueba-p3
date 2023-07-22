import { Component, Inject } from '@angular/core';
import { MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { IConfirmationData } from 'src/app/interfaces/patito.interfaces';

@Component({
  selector: 'app-snack',
  templateUrl: './snack.component.html',
  styleUrls: ['./snack.component.css']
})
export class SnackComponent {
  constructor(@Inject(MAT_SNACK_BAR_DATA) public editModel: IConfirmationData) { }
}
