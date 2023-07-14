import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';

// Define the response interface based on the expected structure of the response from the API
interface ResponseDto {
  // Define the properties based on the response from the API
  // Adjust the types accordingly
}

// Define the DTO (Data Transfer Object) for the title assignment
interface TitlesDto {
  title: string;
  fromDate: string;
  toDate: string;
  empNo: number;
}

@Component({
  selector: 'app-assign-title',
  templateUrl: './assign-title.component.html',
  styleUrls: ['./assign-title.component.css']
})
export class AssignTitleComponent {
  dto: TitlesDto = {
    title: '',
    fromDate: '',
    toDate: '',
    empNo: 0
  };

  response: ResponseDto | null = null;

  constructor(private http: HttpClient) {}

  // Method to get the authorization token from local storage
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  // Method to assign a title
  assignTitle() {
    const headers = this.getToken();

    // Send a POST request to the API endpoint with the title assignment data and headers
    this.http.post<ResponseDto>('http://localhost:9091/api/v1/adminhrmsconsumer/assigntitle', this.dto, { headers })
      .subscribe(
        (response: ResponseDto) => {
          this.response = response;
          console.log("successful");
          alert("Title assigned successfully");
        },
        (error: HttpErrorResponse) => {
          const errorDetails = error.error.errorDetails;
          alert(errorDetails);
          console.log(errorDetails);
        }
      );
  }
}
