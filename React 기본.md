# React 시작하기

##  React 기본 문법

- React 실습해보기

```react
// 설치되어있는 리엑트 모듈 사용
import React, { Component } from 'react';

// Component 만드는 방법
// 1. class이용  2. 함수 생성
class App extends Component {
  render() {
    // jsx 형태의 파일을 return 해야한다
    return (
      // <div></div> 하나로 닫혀있어야 한다.
      // 여러 div를 만들어야한다면 큰 div로 감싸줘야함
      // <Fragment>로 감싸준다면 extra div없이 내용물div만 존재할 수 있음
      <div>
        <h1>안녕하세요 리액트</h1>
      </div>
    );
  }
}

export default App;
```

- 변수를 사용해 출력하기

```react
import React, { Component } from 'react';

class App extends Component {
  render() {
    const name = 'react';
    return <div>Hello {name}</div>;
  }
}

export default App;
```

- 삼항연산자

```react
import React, { Component } from 'react';

class App extends Component {
  render() {
    const name = 'react';
    return (
      // div안에 삼항연산자는 {} 내에 작성해야함
      <div> 
      { name === 'react' ? 'Yes' : 'No' }
      </div>
    );
  }
}

export default App;
```

- if문

```react
import React, { Component } from 'react';

class App extends Component {
  render() {
    const value = 2;
    return (
      <div>
        {
          // function 자체를 ()로 묶어준 후, ()로 호출까지 해야함
          // (function() {})() 이게 하나의 문법!!
          (function() {
          if (value === 1) return <div>1</div>
          if (value === 2) return <div>2</div>
          if (value === 3) return <div>3</div>
          return <div>None</div>
          // else 일 때, -> return <div> </div>에 넣어서 else 호출
          })()
        }
      </div>
    );
  }
}
export default App;
```

<hr>

## React에 CSS 설정

- css 설정

```css
.App {
  background: black;
  color: aqua;
  font-size: 36px;
  padding: 1rem;
  font-weight: 600;
}
```

- css 파일 불러오기

```react
import React, { Component } from 'react';
import './App.css';   // App.css 불러오기

class App extends Component {
  render() {
    return (
	// 위에서 react사용시, class = "App" -> className="App"
    	<div className="App">안녕하세요!</div>
    );
  }
}
export default App;
```

