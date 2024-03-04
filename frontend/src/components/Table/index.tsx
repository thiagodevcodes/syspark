import styles from "./table.module.css"
import Link from "next/link"
import Image from "next/image";

export default function Table({  }) {
    return (
        <div className={styles.container}>
            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>CPF</th>
                        <th>Status</th>
                        <th>Dia Pagamento</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Thiago</td>
                        <td>(79) 98867-4823</td>
                        <td>thiago@gmail.com</td>
                        <td>12345678998</td>
                        <td>Ativo</td>
                        <td>12</td>
                        <td>
                            <button className={`${styles.bgYellow} ${styles.actionButton}`}><Image src={"/icons/Edit.svg"} width={30} height={30} alt="Icone Edit"></Image></button>
                            <button className={`${styles.bgRed} ${styles.actionButton}`}><Image src={"/icons/Remove.svg"} width={30} height={30} alt="Icone Remove"></Image></button>
                            <button className={`${styles.bgBlue} ${styles.actionButton}`}><Image src={"/icons/Cars.svg"} width={30} height={30} alt="Icone Cars"></Image></button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}