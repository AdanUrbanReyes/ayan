import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { LoadingService } from 'src/app/services/loading.service';

@Component({
  selector: 'loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {

  isLoading: Subject<boolean>;

  constructor(private loadingService: LoadingService) {
    this.isLoading = this.loadingService.getIsLoading();
  }

  ngOnInit(): void {
  }

}
