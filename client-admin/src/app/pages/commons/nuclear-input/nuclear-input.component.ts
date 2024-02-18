import {
  Component,
  ElementRef,
  HostBinding,
  Input,
  OnChanges,
  OnDestroy,
  OnInit, Optional, Self,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import {ControlValueAccessor, NgControl, Validators} from "@angular/forms";
import {FocusMonitor} from "@angular/cdk/a11y";
import {TranslateService} from "@ngx-translate/core";
import {BooleanInput} from "ng-zorro-antd/core/types";

@Component({
  selector: 'thd-input',
  templateUrl: './nuclear-input.component.html',
  styleUrls: ['./nuclear-input.component.scss']
})
export class NuclearInputComponent implements OnInit, ControlValueAccessor, OnChanges {
  static nextId = 0;
  @HostBinding()
  id: string = `e-learning-input-${NuclearInputComponent.nextId++}`;
  inputElement: NgControl;
  ngControl: NgControl;
  @ViewChild('inputElements1') set content1(content: NgControl) {
    if (content) {
      this.inputElement = content;
      const validators = this.ngControl?.control?.validator;
      this.inputElement?.control?.setValidators(validators ? validators : null);
    }
  }
  @ViewChild('inputElements2') set content2(content: NgControl) {
    if (content) {
      this.inputElement = content;
      const validators = this.ngControl?.control?.validator;
      this.inputElement?.control?.setValidators(validators ? validators : null);
    }
  }
  @ViewChild('inputElements3') set content3(content: NgControl) {
    if (content) {
      this.inputElement = content;
      const validators = this.ngControl?.control?.validator;
      this.inputElement?.control?.setValidators(validators ? validators : null);
    }
  }
  @Input() size : 'small' | 'default' | 'large' = 'default';
  @Input() isDisabled: boolean = false;
  @Input() hasFeedback: boolean = false;
  @Input() labelSpan: number = null;
  @Input() inputSpan: number = null;
  @Input() errorText: string = "";
  @Input() warningText: string = "";
  @Input() label: string = '';
  @Input() hint: string = '';
  @Input() tooltipTitle: string = null;
  @Input() placeHolder: string = '';
  @Input() type: 'text' | 'number' | 'email' | 'password' | 'date' | 'select' | 'editor' = 'text';
  @Input() items: any[] = [];
  @Input() bindLabel : string = null;
  @Input() bindValue : string = null;
  @Input() allowSearch: boolean = true;
  @Input() isTranslation: boolean = false;
  @Input() allowClear: BooleanInput = false;
  @Input() selectMode: "default" | "multiple" | "tags" = "default";
  @Input('value') val: any;

  get value(): any {
    return this.val;
  }
  set value(val: any) {
    this.val = val;
    this.onChange(val);
    this.onTouched();
  }

  onChange: any = () => {};
  onTouched: any = () => {};

  constructor(public translate: TranslateService, private fm: FocusMonitor, private elRef: ElementRef<HTMLElement>, @Self() @Optional() public control: NgControl) {
    if (this.control) {
      this.ngControl = this.control
      this.control.valueAccessor = this;
    }
  }
  get isRequired(): boolean {
    if(this.ngControl){
      return this.ngControl?.control?.hasValidator(Validators.required);
    }
    return false;
  }
  writeValue(value: any): void {
    if (value) {
      if (this.type == "date") {
        this.value = new Date(value)
      } else {
        this.value = value;
      }
    } else {
      this.value = value;
    }
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    this.isDisabled = isDisabled;
  }

  ngOnInit(): void {
    const validators = this.ngControl?.control?.validator;
    this.inputElement?.control.setValidators(validators ? validators : null);
    this.inputElement?.control.updateValueAndValidity();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.errorText = changes['errorText']?.currentValue;
    this.label = changes['label']?.currentValue?changes['label']?.currentValue:this.label;
    this.placeHolder = changes['placeHolder']?.currentValue?changes['placeHolder']?.currentValue:this.label;
    this.warningText = changes['warningText']?.currentValue?changes['warningText']?.currentValue:this.label;
    this.hint = changes['hint']?.currentValue?changes['hint']?.currentValue:this.label;
    const errors = this.ngControl?.control?.errors;
    this.inputElement?.control?.setErrors(errors ? errors : null);
  }

  compareFn = (o1: any, o2: any): boolean => {
    if (o1 && o2){
      // if(this.bindValue){
      //   return o1===o2 || o1=== o2[this.bindValue]
      // }
      if(o1.value && o2.value){
        return o1.value === o2.value
      }
      if(o1.key && o2.key){
        return o1.key === o2.key
      }
      if(o1.id && o2.id){
        return o1.id === o2.id
      }
      return o1===o2;
    }
    return o1 === o2;
  }

}
