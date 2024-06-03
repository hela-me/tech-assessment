
# Westpac Assessment

This assessment is to create an Android app that uses the below API.

- Fetch the cards and display them in a list.
- For each card, show the card type, card number, and expiry date.
- Feel free to use Compose or XML views, depending on your level of experience with Compose.
- Include a Readme file to list all assumptions and decisions.


## API Reference

#### Get all credit cards


```http
  GET random-data-api.com/api/v2/credit_cards
```

| Query Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `size`      | `int` | **Required**. size of the items. Max is 100 |

## Assumptions

- Localization support is not required.
- This is a smaller scale application. Complex packaging is not required.
- Unit tests are not required.
- Required minimum sdk is 24
- API timeout is 3 seconds according to the SLA
- User friendly messages are not given. i.e. No Internet message.
- Build configurations are not required.
- No CI/CD pipelines available.
- No environment support.
- Modular design is not required.
## Considerations / Decisions
- Developed the app using both XML and Compose.
- Used Dagger Hilt for dependancy injections.
- Used MVVM architecture.
- Reactive programming using Kotlin flows.
- Screen state handling using State Flows.
- Followed SOLID/SSOT/DRY/SRP principles where it's possible.
- Used only a SnackBar to dispaly loading errors.
## Further Improvements

- Build types with the required environment configurations should be added. I haven’t added any config field implementation in this assessment.
- I haven’t created any unit test here, since it was not asked. Btw, dependancy injection provides a great flexibility making the code more unity testable.
- Pagination with a local cache should be applied for long lists whenever it’s possible.
- Add the @Copyright comment to the top of each file. Usually we set it up in Android Studio code editor which will be automatically applied to each file we create.
- Different error messages can be shown for different status codes from the API.
- I have used a simple type based flat packaging structure here, but usually when it comes to large production apps, the packages should easily be able to migrate to multi module project structure. In those cases we should use a context based packaging structure. e.g. consider the following packaging structure
- Custom dimensions/strings/colors should be moved to relavent resources.
- Localization should be applied as required.
## Context Based Packaging Structure

If the project is large scale, the following packaging structure can be considered. Note: Only some of the packages names are mentioned below as examples. More can be added as the development goes on.

Credit Card Context:
- `com.westpac.assesment.creditcard` | credit card context
- `com.westpac.assesment.creditcard.data` | APIs, repositories, dbs, etc..
- `com.westpac.assesment.creditcard.data.api` | API interfaces
- `com.westpac.assesment.creditcard.di` | dependancy injection modules
- `com.westpac.assesment.creditcard.domain`
- `com.westpac.assesment.creditcard.domain.model`
- `com.westpac.assesment.creditcard.domain.validation`
- `com.westpac.assesment.creditcard.view` | activities, fragments, etc..
- `com.westpac.assesment.creditcard.view.components` | custom/composed
Core Context:
- `com.westpac.assesment.core` | core functionalities shared in other contexts
- `com.westpac.assesment.core.data`
- `com.westpac.assesment.core.di`
- `com.westpac.assesment.core.domain`
- `com.westpac.assesment.core.view`