import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Menu } from 'src/app/models/menu';
import { DialogsService } from 'src/app/services/dialogs.service';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  searchForm : FormGroup;
  menus: Menu[] = [];
  options: string[] = ['One', 'Two', 'Three'];
  filteredOptions: Observable<string[]> | undefined;

  constructor(public formBuilder: FormBuilder
    , public dialogsService : DialogsService){
    this.searchForm = formBuilder.group({
      searchString : [undefined]
    });  
  }

  ngOnInit(): void {
    this.menus=[
      new Menu('Catalogs', 'library_books', '/catalogs', [
        new Menu('Read', 'search', '/catalogs')
        , new Menu('Update', 'publish', '/catalogs')
      ])
      , new Menu('Reports', 'pie_chart', '/reports',[
        new Menu('Read', 'search', '/reports')
        , new Menu('Update', 'publish', '/reports')
      ])
    ];
    let ssc = this.searchForm.get('searchString');
    if(ssc != null){
      this.filteredOptions = ssc.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    }
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  search(){
    console.log('searching!...');
  }

}
