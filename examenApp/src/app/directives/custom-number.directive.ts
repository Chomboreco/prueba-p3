import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, ValidationErrors } from '@angular/forms';

@Directive({
  selector: '[cs-number]',
  providers: [{
    provide: NG_VALIDATORS,
    useExisting: CustomNumberDirective,
    multi: true
  }]
})
export class CustomNumberDirective {
  @Input() negative: boolean = false;
  @Input() integers: number = 6;
  @Input() decimals: number = 2;
  private _regex!: RegExp;
  private _len: number = 0;
  private _current: number = 0;

  constructor(private el: ElementRef) { }

  ngOnInit(): void {
    this.el.nativeElement.classList.add('text-right');
    this._len = this.integers + this.decimals + 1;

    let regString: string;
    let d: number = 0;
    let n: string = '';
    let ns: string = `{1,${this.integers}}`;

    if (this.decimals && this.decimals > 0)
      d = 1;

    if (this.negative) {
      n = '-?';
      ns = `{0,${this.integers}}`;
      this._len++;
    }

    regString = `^${n}[0-9]${ns}(\\.[0-9]{0,${this.decimals}}){0,${d}}$`;
    this._regex = new RegExp(regString);
  }

  validate(control: AbstractControl): ValidationErrors | null {
    if (control.value && !String(control.value).match(this._regex))
      return { 'cs-number': true };
    else
      return null;
  }

  @HostListener('keydown', ['$event'])
  onKeyUp(event: KeyboardEvent) {
    this._current = this.el.nativeElement.value;
    // Caracter especial de exponente y espacio
    if (event.key == ' ' || event.key == 'e')
      event.preventDefault();

    // Si es solo enteros
    if (this.decimals <= 0) {
      if (event.key == '.')
        event.preventDefault();

      if (this.integers < (this.el.nativeElement.value.length + 1) && !isNaN(event.key as any))
        event.preventDefault();
    }
  }

  @HostListener('input', ['$event'])
  onInputInput(event: any) {
    // console.log('Data value:', this.el.nativeElement.value);
    // console.log('Event "input":', event);
  }

  @HostListener('blur', ['$event'])
  onInputChange(event: any) {
    // console.log('Data value:', this.el.nativeElement.value);
    // console.log('Event "change":', event);
  }
}
