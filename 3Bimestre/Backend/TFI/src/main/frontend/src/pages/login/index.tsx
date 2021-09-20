import React from 'react';
import { Form, notification } from 'antd';
import { ButtonType, ILoginData, IUser } from '@types';
import { login } from '@services/userService';
import { useUserContext } from '@store';
import { Button, Input } from '@atoms';
import './style.css';

export const LoginPage = () => {
  const { setUser } = useUserContext();
  const [form] = Form.useForm();

  const onValuesChange = (values: ILoginData) => form.setFieldsValue((data: ILoginData) => ({ ...data, ...values }));

  const onSubmit = async (data: ILoginData) => {
    try {
      const user: IUser = await login(data);
      setUser(user);
      notification.success({
        message: `Bienvenido ${user.firstName}`,
      });
    } catch (error) {
      notification.error({
        message: (error as Error).toString(),
      });
    }
  };
  return (
    <Form
      form={form}
      layout="vertical"
      className="login-form"
      requiredMark="optional"
      validateMessages={{ required: 'Campo requerido' }}
      onValuesChange={onValuesChange}
      onFinish={onSubmit}
    >
      <Input name="username" label="Nombre de usuario" className="login-input" required />
      <Input name="password" label="Contraseña" className="login-input" required />
      <Button type={ButtonType.Primary} htmlType="submit">Iniciar sesión</Button>
    </Form>
  );
};
