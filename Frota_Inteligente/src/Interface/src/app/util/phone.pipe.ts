import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'phonePipe'
})
export class TipoPendenciaPipe implements PipeTransform {

  transform(value: string): string {
    if ( value !== null && value !== '') {

    }
    return '';
  }

}
