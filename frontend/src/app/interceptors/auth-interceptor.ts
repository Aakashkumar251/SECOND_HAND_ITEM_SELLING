

                                          
  //                                         Component
  //  ↓
  //                                         HttpClient
  //                                            ↓
  //                                         Interceptor
  //                                            ↓
  //                                         Spring Boot API
                                          
                                          
                                          










import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => { // This function is an HTTP interceptor that adds an Authorization header with
//  a Bearer token to outgoing HTTP requests if a token is present in local storage.
  const token = localStorage.getItem('token'); // The function retrieves the token from local storage. If a token exists,
  //  it clones the original HTTP request and sets the Authorization header with the Bearer token. The modified request is
  //  then passed to the next handler in the chain. If no token is found, the original request is passed through without modification.

  if (token) {
    const cloned = req.clone({    // The original HTTP request is cloned to avoid mutating it directly, which is a best practice in Angular.
      setHeaders: {                 // The setHeaders option is used to add the Authorization header to the cloned request. The header value is formatted as "Bearer <token>".
        Authorization: `Bearer ${token}` // The Authorization header is commonly used for token-based authentication schemes,
        //  where the token is sent in the header of each request to authenticate the user.
      }
    });
    return next(cloned); // The modified request with the Authorization header is passed to the next handler
    //  in the HTTP request pipeline using next(cloned).
  }

  return next(req); // If no token is found in local storage, the original HTTP request is
  //  passed through to the next handler without modification using next(req).
};