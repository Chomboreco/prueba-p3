import { trigger, state, style, transition, animate } from '@angular/animations';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidenav-container',
  templateUrl: './sidenav-container.component.html',
  styleUrls: ['./sidenav-container.component.css'],
  animations: [
    trigger('slideInOut', [
      state('open', style({
        opacity: '1',
        height: '*'
      })),
      state('closed', style({
        opacity: '0',
        height: '0px'
      })),
      transition('open => closed', animate('150ms ease-in-out')),
      transition('closed => open', animate('150ms ease-in-out'))
    ]),
    trigger('rotate', [
      state('open', style({
        transform: 'rotate(90deg)'
      })),
      state('closed', style({
        transform: 'rotate(0deg)'
      })),
      transition('open => closed', animate('150ms ease-in-out')),
      transition('closed => open', animate('150ms ease-in-out'))
    ])
  ]
})
export class SidenavContainerComponent implements OnInit {
  submenuState: string = 'closed';

  ngOnInit(): void { }

  toggleSubmenu(): void {
    this.submenuState = this.submenuState === 'open' ? 'closed' : 'open';
  }

}
