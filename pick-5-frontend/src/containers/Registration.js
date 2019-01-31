import React, { Component } from "react";
import {
  HelpBlock,
  FormGroup,
  FormControl,
  ControlLabel
} from "react-bootstrap";
// import { Auth } from "aws-amplify";
import LoaderButton from "../components/LoaderButton";
import "./Registration.css";
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import {submitUserRegistration} from '../modules/request'

class Registration extends Component {
  constructor(props) {
   
    super(props);
    
        this.state = {
        //  isRegistering: false,
            email: "",
            password: "",
            confirmPassword: "",
            confirmationCode: "",
            newUser: null,
            userName: ""
    };
  }

  validateForm() {
    return (
      this.state.email.length > 0 &&
      this.state.password.length > 0 &&
      this.state.password === this.state.confirmPassword
    );
  }

  validateConfirmationForm() {
    return this.state.confirmationCode.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = async event => {
    event.preventDefault();
    const userInfo = {
        username: this.state.username,
        email : this.state.email,
        password : this.state.password
    }
    //TODO: move to props 
    // this.setState({ isLoading: true });

    try {
        this.props.submitUserRegistration(userInfo);

    //   const newUser = await Auth.confirmRegistration({
    //     username: this.state.email,
    //     password: this.state.password
    //   });
    // const newUser = {email:'email',password:'password '}
    //   this.setState({
    //     newUser
    //   });
    } catch (e) {
      alert(e.message);
    }

    // this.setState({ isLoading: false });
  }

  handleConfirmationSubmit = async event => {
    event.preventDefault();

    // this.setState({ isLoading: true });

    try {
    //   await Auth.confirmconfirmRegistration(this.state.email, this.state.confirmationCode);
    //   await Auth.signIn(this.state.email, this.state.password);

      this.props.userHasAuthenticated(true);
      this.props.history.push("/");
    } catch (e) {
      alert(e.message);
    //   this.setState({ isLoading: false });
    }
  }

  renderConfirmationForm() {
    return (
      <form onSubmit={this.handleConfirmationSubmit}>
        <FormGroup controlId="confirmationCode" bsSize="large">
          <ControlLabel>Confirmation Code</ControlLabel>
          <FormControl
            autoFocus
            type="tel"
            value={this.state.confirmationCode}
            onChange={this.handleChange}
          />
          <HelpBlock>Please check your email for the code.</HelpBlock>
        </FormGroup>
        <LoaderButton
          block
          bsSize="large"
          disabled={!this.validateConfirmationForm()}
          type="submit"
          isLoading={this.props.isRegistering}
          text="Verify"
          loadingText="Verifying…"
        />
      </form>
    );
  }

  renderForm() {
    return (
      <form onSubmit={this.handleSubmit}>
        <FormGroup controlId="userName" bsSize="small">
          <ControlLabel>User Name</ControlLabel>
          <FormControl
            autoFocus
            type="userName"
            value={this.state.userName}
            onChange={this.handleChange}
          />
        </FormGroup>
        <FormGroup controlId="email" bsSize="small">
          <ControlLabel>Email</ControlLabel>
          <FormControl
            autoFocus
            type="email"
            value={this.state.email}
            onChange={this.handleChange}
          />
        </FormGroup>
      
        <FormGroup controlId="password" bsSize="small">
          <ControlLabel>Password</ControlLabel>
          <FormControl
            value={this.state.password}
            onChange={this.handleChange}
            type="password"
          />
        </FormGroup>
        <FormGroup controlId="confirmPassword" bsSize="small">
          <ControlLabel>Confirm Password</ControlLabel>
          <FormControl
            value={this.state.confirmPassword}
            onChange={this.handleChange}
            type="password"
          />
        </FormGroup>
        <LoaderButton
          block
          bsSize="large"
          disabled={!this.validateForm()}
          type="submit"
          isLoading={this.props.isRegistering}
          text="Register New User"
          loadingText="Signing up…"
        />
      </form>
    );
  }

  render() {
    
    return (
      <div className="registration">
        {this.state.newUser === null
          ? this.renderForm()
          : this.renderConfirmationForm()}
        
      </div> 
    );
  }
}

const mapStateToProps = ({ request }) => ({
    request: request.isRegistering
  })
  
  const mapDispatchToProps = dispatch =>

    bindActionCreators({submitUserRegistration},dispatch)
  
  export default connect(
    mapStateToProps,
    mapDispatchToProps
  )(Registration)
  