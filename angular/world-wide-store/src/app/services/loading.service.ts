import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private isLoading = new Subject<boolean>();

  constructor() { }

  getIsLoading(): Subject<boolean> {
		return this.isLoading;
	}

	show() {
		this.isLoading.next(true);
	}

	hide() {
		this.isLoading.next(false);
	}

}
